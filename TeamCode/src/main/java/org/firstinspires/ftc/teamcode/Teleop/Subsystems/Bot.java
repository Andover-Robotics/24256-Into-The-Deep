package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;



@TeleOp
public class Bot {

    public OpMode opMode;
    public static Bot instance;

    public IntakeArm intakeArm;
    public IntakeClaw intakeClaw;

    public OuttakeClaw outtakeClaw;
    public OuttakeArm outtakeArm;

    public Slides slides;

    public DcMotorEx bl, br, fl, fr;
    public boolean fieldCentricRunMode = false;

    public Bot(OpMode opMode) {
        this.opMode = opMode;

        try {
            fieldCentricRunMode = false;
        }catch (Exception e) {
            fieldCentricRunMode=false;
        }
        fl = opMode.hardwareMap.get(DcMotorEx.class,"fl");
        fr = opMode.hardwareMap.get(DcMotorEx.class,"fr");
        bl = opMode.hardwareMap.get(DcMotorEx.class,"bl");
        br = opMode.hardwareMap.get(DcMotorEx.class,"br");

        prepMotors();

        this.intakeArm = new IntakeArm(opMode);
        this.intakeClaw = new IntakeClaw(opMode);
        this.outtakeArm = new OuttakeArm(opMode);
        this.outtakeClaw = new OuttakeClaw(opMode);

    }

    public static Bot getInstance(OpMode opMode) {
        if (instance == null) {
            return instance = new Bot(opMode);
        }
        instance.opMode = opMode;
        return instance;
    }

    public void driveRobotCentric(double strafeSpeed, double forwardBackSpeed, double turnSpeed) {
        double[] speeds = {
                (forwardBackSpeed - strafeSpeed - turnSpeed),
                (forwardBackSpeed + strafeSpeed + turnSpeed),
                (forwardBackSpeed + strafeSpeed - turnSpeed),
                (forwardBackSpeed - strafeSpeed + turnSpeed)
        };
        double maxSpeed = 0;
        for (int i = 0; i < 4; i++) {
            maxSpeed = Math.max(maxSpeed, speeds[i]);
        }
        if (maxSpeed > 1) {
            for (int i = 0; i < 4; i++) {
                speeds[i] /= maxSpeed;
            }
        }
        fl.setPower(speeds[0]);
        fr.setPower(speeds[1]);
        bl.setPower(speeds[2]);
        br.setPower(speeds[3]);
    }
    public void prepMotors() {
        intakeArm.armServoL.setDirection(Servo.Direction.REVERSE);
        outtakeArm.bucketServoL.setDirection(Servo.Direction.REVERSE);
        outtakeClaw.topWrist.setDirection(Servo.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void resetEverything(){
        intakeArm.armToIntakePos();
        outtakeArm.armToAfterTransfer1Pos();
        intakeClaw.openClaw();
        outtakeClaw.outtakeClawOpen();
        intakeClaw.wristToIntakePos();
        outtakeClaw.topWristTransferPos();
    }

    public void resetEncoders() {
        fl.setMode(STOP_AND_RESET_ENCODER);
        fr.setMode(STOP_AND_RESET_ENCODER);
        br.setMode(STOP_AND_RESET_ENCODER);
        bl.setMode(STOP_AND_RESET_ENCODER);
    }
    public void resetIntake(){
        intakeArm.armToIntakePos();
        intakeClaw.wristToIntakePos();
    }
    public void resetOuttake(){
        outtakeArm.armToAfterTransfer1Pos();
        outtakeClaw.outtakeClawOpen();
        outtakeClaw.topWristTransferPos();
    }
    public void goToTransferPos(ElapsedTime time){
        time.reset();
        intakeArm.armToTransferPos();
        while(time.seconds() <0.5);
        intakeClaw.wristToTransferPos();
        while(time.seconds() <0.5);
        outtakeClaw.outtakeClawClose();
        while(time.seconds() <0.5);
        intakeClaw.openClaw();
        while(time.seconds() <0.5);
    }
    public void goToOuttakePos(ElapsedTime time) {
        time.reset();
        outtakeArm.armToTransfer2Pos();
        while(time.seconds() <0.5);
        outtakeClaw.topWristToOuttakePos();
        while(time.seconds() <0.5);
        outtakeClaw.outtakeClawOpen();
    }

}
