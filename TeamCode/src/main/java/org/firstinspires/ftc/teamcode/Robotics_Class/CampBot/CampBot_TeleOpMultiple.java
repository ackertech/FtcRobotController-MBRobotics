package org.firstinspires.ftc.teamcode.Robotics_Class.CampBot;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//@Disabled
@TeleOp(name = "CampBot: Two Driver")

public class CampBot_TeleOpMultiple extends OpMode {

    //TeleOp Driving Behavior Variables
    public double speedMultiply = 1.0;
    public enum Style {
        ARCADE1, ARCADE2, TANK
    }
    public Style driverStyle = Style.ARCADE1;

    public enum LoadStates {
        READY, OPEN_GATE, DELAY1, LOAD_BUCKET, DELAY2, UNLOAD_BUCKET, DELAY3, CLOSE_GATE
    }

    public LoadStates loadState = LoadStates.READY;

    ElapsedTime timer = new ElapsedTime();

    // GamePad Variables
    public float leftStickY1;
    public float rightStickY1;
    public float leftStickX1;
    public float rightStickX1;

    public double leftMotorValue;
    public double rightMotorValue;
    public double leftSidePower;
    public double rightSidePower;

    //
    public boolean servo1Ready = true;
    public boolean servo2Ready = true;
    public boolean servo3Ready= true;


    // Construct the Physical Bot based on the Robot Class
    public CampBot Bot = new CampBot();


    // TeleOp Initialize Method.  This is the Init Button on the Driver Station Phone
    @Override
    public void init() {

        Bot.initDrive(hardwareMap);
        Bot.initMotors(hardwareMap);
        Bot.initServo1(hardwareMap);
        Bot.initServo2(hardwareMap);
        Bot.initServo3(hardwareMap);

        leftStickY1 = 0;
        leftStickX1 = 0;
        rightStickY1 = 0;
        rightStickX1 = 0;

    }

    // TeleOp Loop Method.  This start AFTER clicking the Play Button on the Driver Station Phone

    public void loop() {

        //Drive Controller Methods
        getController();
        speedControl();
        driveControl();

        //Manual Mechanism Controller Methods
        motor1Control();
        motor2Control();
        servoOneControl();
        servoTwoControl();
        servoThreeControl();

        //Load Controller Methods using States
        loadStateControl();

        //Telemetry Controller
        telemetryOutput();

    }

    /**  ********  TELEMETRY OUTPUT *************      **/

    public void telemetryOutput() {
        telemetry.addData("Drive Mode: ", driverStyle);
        telemetry.addData("Speed: ", speedMultiply);
        telemetry.addData("Left Motor Power: ", Bot.driveLeftMotor.getPower());
        telemetry.addData("Right Motor Power: ", Bot.driveRightMotor.getPower());
        telemetry.addData("Motor 1: ", Bot.motor1.getPower());
        telemetry.addData("Motor 2: ", Bot.motor2.getPower());
        //telemetry.addData("Worm Gear: ", Bot.wormGear.getPower());
        //telemetry.addData("Linear Actuator: ", Bot.linearActuator.getPower());
        telemetry.addData("Servo 1: ", Bot.servo1.getPosition());
        telemetry.addData("Servo 2: ", Bot.servo2.getPosition());
        telemetry.addData("Servo 3: ", Bot.servo3.getPosition());
        telemetry.update();

    }

    /**  ********  DRIVING METHODS USING GAMEPAD 1 *************      **/

    public void getController() {
        leftStickY1 = -gamepad1.left_stick_y;
        leftStickX1 = gamepad1.left_stick_x;
        rightStickY1 = -gamepad1.right_stick_y;
        rightStickX1 = gamepad1.right_stick_x;
    }

    public void driveControl() {

        if (gamepad1.a) {
            driverStyle = Style.ARCADE1;
        }
        if (gamepad1.b) {
            driverStyle = Style.ARCADE2;
        }
        if (gamepad1.y) {
            driverStyle = Style.TANK;
        }

        switch (driverStyle) {

            case ARCADE1:

                leftMotorValue = leftStickY1 - leftStickX1;
                rightMotorValue = leftStickY1 + leftStickX1;
                leftMotorValue = Range.clip(leftMotorValue, -1, 1);
                rightMotorValue = Range.clip(rightMotorValue, -1, 1);
                Bot.driveLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.driveRightMotor.setPower(rightMotorValue * speedMultiply);
                break;

            case ARCADE2:
                leftMotorValue = leftStickY1 - rightStickX1;
                rightMotorValue = leftStickY1 + rightStickX1;
                leftMotorValue = Range.clip(leftMotorValue, -1, 1);
                rightMotorValue = Range.clip(rightMotorValue, -1, 1);
                Bot.driveLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.driveRightMotor.setPower(rightMotorValue * speedMultiply);
                break;

            case TANK:

                double powerFLM = leftStickY1 * speedMultiply;
                double powerRLM = leftStickY1 * speedMultiply;
                double powerFRM = rightStickY1 * speedMultiply;
                double powerRRM = rightStickY1 * speedMultiply;

                Bot.driveLeftMotor.setPower(powerRLM);
                Bot.driveRightMotor.setPower(powerRRM);

                break;
        }
    }

    public void speedControl () {
            if (gamepad1.a) {
                speedMultiply = 0.50;
            } else if (gamepad1.b) {
                speedMultiply = 1.00;
            }
    }

    /**  ********  DRIVING METHODS USING GAMEPAD 2 *************      **/


    public void motor1Control()
    {
        if (gamepad2.left_trigger > 0.1) {
            Bot.rotateMotor1(1.0);
        }
        else if (gamepad2.right_trigger > 0.1) {
            Bot.rotateMotor1(-1.0);
        }
        else
        {
            Bot.stopMotor1();
        }
    }
    public void motor2Control()
    {
        if (gamepad2.y) {
            Bot.rotateMotor2(1.0);
        }
        else if (gamepad2.b) {
            Bot.rotateMotor2(-1.0);
        }
        else
        {
            Bot.stopMotor2();
        }
    }


    // Flag or Rubber Duck Hook
    public void servoOneControl() {
        if (gamepad2.left_bumper) {
            Bot.extendServo1();
        }

        if (gamepad2.right_bumper) {
            Bot.retractServo1();
        }
    }

   // Rubber Duck Bucket Lift
    public void servoTwoControl() {
        if (gamepad2.dpad_up) {
               loadStateControl();
        }

    }

    // Rubber Duck Capture Gate
    public void servoThreeControl() {
        if (gamepad2.dpad_left) {
            Bot.extendServo3();
        }

        if (gamepad2.dpad_right) {
            Bot.retractServo3();
        }
    }

    // State Controller
    public void loadStateControl() {
        switch (loadState) {
            case OPEN_GATE:
                Bot.extendServo3();
                loadState = LoadStates.DELAY1;
                timer.reset();
                break;
            case DELAY1:
                if (timer.time() > .5) {
                    loadState = LoadStates.UNLOAD_BUCKET;
                }
                break;
            case UNLOAD_BUCKET:
                Bot.retractServo2();
                loadState = LoadStates.DELAY2;
                timer.reset();
                break;
            case DELAY2:
                if (timer.time() > .5) {
                    loadState = LoadStates.LOAD_BUCKET;
                }
                break;
            case LOAD_BUCKET:
                Bot.extendServo2();
                loadState = LoadStates.DELAY3;
                timer.reset();
                break;
            case DELAY3:
                if (timer.time() > .5) {
                    loadState = LoadStates.CLOSE_GATE;
                }
                break;
            case CLOSE_GATE:
                Bot.retractServo3();
                loadState = LoadStates.READY;
                break;
            case READY:
                break;
        }
    }



}
