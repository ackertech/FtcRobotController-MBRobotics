package org.firstinspires.ftc.teamcode.Robotics_Class.CampBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CampBot_Drive {


    public DcMotor driveLeftMotor;
    public DcMotor driveRightMotor;

    public LinearOpMode LinearOp = null;

    public CampBot_Drive(){}

    public void setLinearOp(LinearOpMode LinearOp) {this.LinearOp = LinearOp;}

    public void setMotorRunModes(DcMotor.RunMode mode) {

        driveLeftMotor.setMode(mode);
        driveRightMotor.setMode(mode);
    }

    public void stopMotors() {
        driveRightMotor.setPower(0);
        driveLeftMotor.setPower(0);
    }

    public void driveForward(double speed) {
        driveLeftMotor.setPower(-speed);
        driveRightMotor.setPower(-speed);
    }

    public void driveBack(double speed) {
        driveLeftMotor.setPower(speed);
        driveRightMotor.setPower(speed);
    }

    public void rotateLeft(double speed) {
        driveLeftMotor.setPower(speed);
        driveRightMotor.setPower(-speed);

    }

    public void rotateRight(double speed) {
        driveLeftMotor.setPower(-speed);
        driveRightMotor.setPower(speed);

    }



    public void  tankDrive (double leftPower, double rightPower) {
        driveLeftMotor.setPower(leftPower);
        driveRightMotor.setPower(rightPower);
    }
}


