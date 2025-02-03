package org.firstinspires.ftc.teamcode.Robotics_Class.CandyBot;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

//@Disabled
@TeleOp(name = "CandyBot",group="iLab")

public class CandyBot_TeleOp extends OpMode {

    //TeleOp Driving Behavior Variables
    public double speedMultiply = 1;
    public enum Style {
        ARCADE1, ARCADE2, TANK
    }

    public Style driverStyle = Style.ARCADE1;

    // GamePad Variables
    public float leftStickY1;
    public float rightStickY1;
    public float leftStickX1;
    public float rightStickX1;

    public double leftMotorValue;
    public double rightMotorValue;
    public double leftSidePower;
    public double rightSidePower;

    // Construct the Physical Bot based on the Robot Class
    public CandyBot Bot = new CandyBot();


    // TeleOp Initialize Method.  This is the Init Button on the Driver Station Phone
    @Override
    public void init() {

        Bot.initDrive(hardwareMap);
        Bot.initFlyWheels(hardwareMap);
        Bot.initLinearActuator(hardwareMap);
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

        //Drive Comtroller Methods
        getController();
        speedControl();
        driveControl();

        //Mechanism Comtroller Methods
        flyWheelControl();
        linearActuatorControl();
        wormGearControl();
        servoOneControl();
        servoTwoControl();
        servoThreeControl();

        //Telemetry Controller
        telemetryOutput();

    }

    /**  ********  TELEMETRY OUTPUT *************      **/

    public void telemetryOutput() {
        telemetry.addData("Drive Mode: ", driverStyle);
        telemetry.addData("Speed: ", speedMultiply);
        telemetry.addData("Front Left Motor Power: ", Bot.frontLeftMotor.getPower());
        telemetry.addData("Rear Left Motor Power: ", Bot.rearLeftMotor.getPower());
        telemetry.addData("Front Right Motor Power: ", Bot.frontRightMotor.getPower());
        telemetry.addData("Rear Right Motor Power: ", Bot.rearRightMotor.getPower());
        telemetry.addData("Fly Wheel 1: ", Bot.flyWheel1.getPower());
        telemetry.addData("Fly Wheel 2: ", Bot.flyWheel2.getPower());
        telemetry.addData("Worm Gear: ", Bot.wormGear.getPower());
        telemetry.addData("Linear Actuator: ", Bot.linearActuator.getPower());
        telemetry.addData("Servo 1: ", Bot.servo1.getPosition());
        telemetry.addData("Servo 2: ", Bot.servo2.getPosition());
        telemetry.addData("Servo 3: ", Bot.servo3.getPosition());
        telemetry.update();

    }

    /**  ********  DRIVING METHODS USING GAMEPAD 1 *************      **/

    public void getController() {
        leftStickY1 = gamepad1.left_stick_y;
        leftStickX1 = gamepad1.left_stick_x;
        rightStickY1 = gamepad1.right_stick_y;
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
                Bot.frontLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.rearLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.frontRightMotor.setPower(rightMotorValue * speedMultiply);
                Bot.rearRightMotor.setPower(rightMotorValue * speedMultiply);
                break;

            case ARCADE2:
                leftMotorValue = leftStickY1 - rightStickX1;
                rightMotorValue = leftStickY1 + rightStickX1;
                leftMotorValue = Range.clip(leftMotorValue, -1, 1);
                rightMotorValue = Range.clip(rightMotorValue, -1, 1);
                Bot.frontLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.rearLeftMotor.setPower(leftMotorValue * speedMultiply);
                Bot.frontRightMotor.setPower(rightMotorValue * speedMultiply);
                Bot.rearRightMotor.setPower(rightMotorValue * speedMultiply);
                break;

            case TANK:

                double powerFLM = leftStickY1 * speedMultiply;
                double powerRLM = leftStickY1 * speedMultiply;
                double powerFRM = rightStickY1 * speedMultiply;
                double powerRRM = rightStickY1 * speedMultiply;

                Bot.frontLeftMotor.setPower(powerFLM);
                Bot.rearLeftMotor.setPower(powerRLM);
                Bot.frontRightMotor.setPower(powerFRM);
                Bot.rearRightMotor.setPower(powerRRM);

                break;
        }
    }

    public void speedControl () {
            if (gamepad1.dpad_right) {
                speedMultiply = 0.25;
            } else if (gamepad1.dpad_right) {
                speedMultiply = 0.50;
            } else if (gamepad1.dpad_down) {
                speedMultiply = 0.75;
            } else if (gamepad1.dpad_left) {
                speedMultiply = 1.00;
            }
    }

    /**  ********  DRIVING METHODS USING GAMEPAD 2 *************      **/


    public void flyWheelControl()
    {
        if (gamepad2.left_bumper) {
            Bot.rotateFlyWheel1(1.0);
            Bot.rotateFlyWheel2(-1.0);
        }

        if (gamepad2.right_bumper) {
            Bot.stopFlyWheel1();
            Bot.stopFlyWheel2();
        }

    }

    public void wormGearControl()
    {
        if (gamepad2.left_trigger > 0.1) {
            Bot.wormGearRotateForward(0.90);
        }
        else if (gamepad2.right_trigger > 0.1) {
            Bot.wormGearRotateReverse(-0.90);
        }
        else
        {
            Bot.wormGearStop();
        }
    }

    public void linearActuatorControl() {
        if (gamepad2.dpad_up) {
            Bot.extendLinear(.90);
        }
        else if (gamepad2.dpad_up) {
            Bot.retractLinear(90);
        }
        else
        {
            Bot.stopLinear();
        }

    }

    public void servoOneControl() {
        if (gamepad2.y) {
            Bot.extendServo1();
        }

        if (gamepad2.x) {
            Bot.retractServo1();
        }
    }

    public void servoTwoControl() {
        if (gamepad2.a) {
            Bot.extendServo2();
        }

        if (gamepad2.b) {
            Bot.retractServo2();
        }
    }

    public void servoThreeControl() {
        if (gamepad2.dpad_left) {
            Bot.extendServo3();
        }

        if (gamepad2.dpad_right) {
            Bot.retractServo3();
        }
    }




}
