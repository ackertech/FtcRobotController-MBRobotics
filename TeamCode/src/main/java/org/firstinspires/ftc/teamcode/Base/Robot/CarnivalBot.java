package org.firstinspires.ftc.teamcode.Base.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Base.Drivetrains.Carnival_Drive;

public class CarnivalBot extends Carnival_Drive {

    public HardwareMap hwBot = null;

    // Motors
    public DcMotor wormGear;
    public DcMotor linearActuator;
    public DcMotor motor1;
    public DcMotor motor2;


    // Servos
    public Servo servo2 = null;
    public Servo servo1;
    public Servo servo3;

    public CarnivalBot() {}

    // **** Initialize Drivetrain Hardware ****
    public void initDrive(HardwareMap hwMap) {
        hwBot = hwMap;

        // Drive Motors
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor"); //Port 0
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");// Port 2
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");// Port 1
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");// Port 3

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    // **** Initialize Fly Wheel Hardware ****
    public void initMotorss(HardwareMap hwMap) {
        hwBot = hwMap;
        motor1 = hwBot.dcMotor.get("motor1");
        motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor2 = hwBot.dcMotor.get("motor2");
        motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // **** Initialize Worm Gear Mechanism ****
    public void initWormGear(HardwareMap hwMap) {
        hwBot = hwMap;
        wormGear = hwBot.dcMotor.get("worm_gear");
        wormGear.setDirection(DcMotorSimple.Direction.FORWARD);
        wormGear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Linear Actuator  ****
    public void initLinearActuator(HardwareMap hwMap) {
        hwBot = hwMap;
        linearActuator = hwBot.dcMotor.get("linear_actuator");
        linearActuator.setDirection(DcMotorSimple.Direction.FORWARD);
        linearActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActuator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // **** Initialize Balloon Popper Hardware ****
    public void initServo1(HardwareMap hwMap) {
        hwBot = hwMap;
        servo1 = hwBot.get(Servo.class, "servo1");
        servo1.setDirection(Servo.Direction.FORWARD);
    }

    // **** Initialize Disc Pusher Hardware ****
    public void initServo2(HardwareMap hwMap) {
        hwBot = hwMap;
        servo2 = hwBot.get(Servo.class, "servo2");
        servo2.setDirection(Servo.Direction.FORWARD);
    }

    // **** Initialize Additional Servo Hardware ****
    public void initServo3(HardwareMap hwMap) {
        hwBot = hwMap;
        servo3 = hwBot.get(Servo.class, "servo3");
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

    // **** Movement Methods for Servo 2 ****
    public void extendServo2() {
        servo2.setPosition(0.8);
    }
    public void extendServo2Partially() {
        servo2.setPosition(0.5);
    }
    public void retractServo2() {
        servo2.setPosition(0.2);
    }


    // **** Movement Methods for Servo 1 ****
    public void extendServo1() { servo1.setPosition(0.8); }
    public void extendServo1Partially() {
        servo2.setPosition(0.5);
    }
    public void retractServo1() {servo1.setPosition(0.2);
    }


    // **** Movement Methods for Servo 3 ****
    public void extendServo3() { servo3.setPosition(0.8);}
    public void extendServo3Partially() { servo3.setPosition(0.5);}
    public void retractServo3() { servo3.setPosition(0.2);}



}