package org.firstinspires.ftc.teamcode.Robotics_Class.CandyBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Base.Drivetrains.Carnival_Drive;

public class CandyBot extends CandyBot_Drive {

    public HardwareMap hwBot = null;

    // Motors for Mechanisms
    public DcMotor wormGear;
    public DcMotor linearActuator;
    public DcMotor flyWheel1;
    public DcMotor flyWheel2;


    // Servos for Mechanisms
    public Servo servo1;
    public Servo servo2;
    public Servo servo3;

    // Constructor for Physical Robot
    public CandyBot() {}

    // **** Initialize Drivetrain Hardware ****
    public void initDrive(HardwareMap hwMap) {
        hwBot = hwMap;

        // Drive Motors
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");     // Control Hub Port 0
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");   // Control Hub Port 2
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");       // Control Hub Port 1
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");     // Control Hub Port 3

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // **** Initialize Fly Wheel Hardware ****
    public void initFlyWheels(HardwareMap hwMap) {
        hwBot = hwMap;
        flyWheel1 = hwBot.dcMotor.get("fly_wheel1");                        //Expansion Port 0
        flyWheel1.setDirection(DcMotorSimple.Direction.FORWARD);
        flyWheel1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flyWheel2 = hwBot.dcMotor.get("fly_wheel2");                        //Expansion Port 1
        flyWheel2.setDirection(DcMotorSimple.Direction.FORWARD);
        flyWheel2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // **** Initialize Worm Gear Mechanism ****
    public void initWormGear(HardwareMap hwMap) {
        hwBot = hwMap;
        wormGear = hwBot.dcMotor.get("worm_gear");                          //Expansion Port 2
        wormGear.setDirection(DcMotorSimple.Direction.FORWARD);
        wormGear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Linear Actuator  ****
    public void initLinearActuator(HardwareMap hwMap) {
        hwBot = hwMap;
        linearActuator = hwBot.dcMotor.get("linear_actuator");             //Expansion Port 3
        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Servo  Hardware ****
    public void initServo1(HardwareMap hwMap) {
        hwBot = hwMap;
        servo1 = hwBot.get(Servo.class, "servo1");          //Control Hub Servo Port 0
        servo1.setDirection(Servo.Direction.FORWARD);
    }

    public void initServo2(HardwareMap hwMap) {
        hwBot = hwMap;
        servo2 = hwBot.get(Servo.class, "servo2");          //Control Hub Servo Port 1
        servo2.setDirection(Servo.Direction.FORWARD);
    }


    public void initServo3(HardwareMap hwMap) {
        hwBot = hwMap;
        servo3 = hwBot.get(Servo.class, "servo3");          //Control Hub Servo Port 2
        servo3.setDirection(Servo.Direction.FORWARD);
    }


    // **** Movement Methods for Fly Wheels ****
    public void rotateFlyWheel1 (double power) {
        flyWheel1.setPower(power);
    }

    public void rotateFlyWheel2 (double power) {
        flyWheel2.setPower(power);
    }

    public void stopFlyWheel1 () {
        flyWheel1.setPower(0);
    }

    public void stopFlyWheel2 () {
        flyWheel2.setPower(0);
    }

    // **** Movement Methods for Worm Gear ****
    public void wormGearRotateForward (double power) {
        wormGear.setPower(Math.abs(power));
    }

    public void wormGearRotateReverse (double power) {
        wormGear.setPower(-Math.abs(power));
    }

    public void wormGearStop () {
        wormGear.setPower(0);
    }

    // **** Movement Methods for Linear Actuator ****
    public void extendLinear (double power) {
        linearActuator.setPower(Math.abs(power));
    }

    public void retractLinear (double power) {
        linearActuator.setPower(-Math.abs(power));
    }

    public void stopLinear () {
        linearActuator.setPower(0);
    }

    // **** Movement Methods for Servos ****
    public void extendServo1() {
        servo1.setPosition(0.8);
    }
    public void extendServo1Partially() {
        servo1.setPosition(0.5);
    }
    public void retractServo1() {
        servo1.setPosition(0.2);
    }

    public void extendServo2() {
        servo2.setPosition(0.8);
    }
    public void extendServo2Partially() {
        servo2.setPosition(0.5);
    }
    public void retractServo2() {
        servo2.setPosition(0.2);
    }

    public void extendServo3() {
        servo3.setPosition(0.8);
    }
    public void extendServo3Partially() {
        servo3.setPosition(0.5);
    }
    public void retractServo3() {
        servo3.setPosition(0.2);
    }








}