package org.firstinspires.ftc.teamcode.Robotics_Class.CampBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CampBot extends CampBot_Drive {

    public HardwareMap hwBot = null;

    // Motors for Mechanisms
    public DcMotor wormGear;
    public DcMotor linearActuator;
    public DcMotor motor1;
    public DcMotor motor2;


    // Servos for Mechanisms
    public Servo servo1;
    public Servo servo2;
    public Servo servo3;

    // Constructor for Physical Robot
    public CampBot() {}

    // **** Initialize Drivetrain Hardware ****
    public void initDrive(HardwareMap hwMap) {
        hwBot = hwMap;

        // Drive Motors
        driveLeftMotor = hwBot.dcMotor.get("left_motor");       // Control Hub Port 1
        driveRightMotor = hwBot.dcMotor.get("right_motor");     // Control Hub Port 3

        driveLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        driveRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        driveLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // **** Initialize Fly Wheel Hardware ****
    public void initMotors(HardwareMap hwMap) {
        hwBot = hwMap;
        motor1 = hwBot.dcMotor.get("motor1");                        //Expansion Port 0
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2 = hwBot.dcMotor.get("motor2");                        //Expansion Port 1
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
    public void rotateMotor1(double power) {
        motor1.setPower(power);
    }

    public void rotateMotor2(double power) {

        motor2.setPower(power);
    }

    public void stopMotor1() {

        motor1.setPower(0);
    }

    public void stopMotor2() {
        motor2.setPower(0);
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