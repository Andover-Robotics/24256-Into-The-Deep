package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import  com.qualcomm.robotcore.hardware.Servo;


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
        } catch (Exception e) {
            fieldCentricRunMode = false;
        }
        fl = opMode.hardwareMap.get(DcMotorEx.class, "fl");
        fr = opMode.hardwareMap.get(DcMotorEx.class, "fr");
        bl = opMode.hardwareMap.get(DcMotorEx.class, "bl");
        br = opMode.hardwareMap.get(DcMotorEx.class, "br");

        this.intakeArm = new IntakeArm(opMode);
        this.intakeClaw = new IntakeClaw(opMode);
        this.outtakeArm = new OuttakeArm(opMode);
        this.outtakeClaw = new OuttakeClaw(opMode);
        this.slides = new Slides(opMode);


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
                (forwardBackSpeed - strafeSpeed + turnSpeed),
                (forwardBackSpeed + strafeSpeed - turnSpeed),
                (-forwardBackSpeed - strafeSpeed - turnSpeed),
                (-forwardBackSpeed + strafeSpeed + turnSpeed)
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
        bl.setPower(-speeds[2]);
        br.setPower(-speeds[3]);
    }

    public void prepMotors() {

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void prepSubsystems() {
        intakeArm.armServoL.setDirection(Servo.Direction.REVERSE);
        slides.slideMotorR.setInverted(true);
        slides.slideMotorL.setInverted(false);
        outtakeArm.bucketServoR.setDirection(Servo.Direction.REVERSE);
        outtakeClaw.topWrist.setDirection(Servo.Direction.REVERSE);
        intakeClaw.wristServo.setDirection(Servo.Direction.REVERSE);
    }

    public void resetEverything() {
        intakeArm.armToUpPos();
        intakeClaw.openClaw();
        outtakeClaw.outtakeClawOpen();
        intakeClaw.wristToIntakePos();
        outtakeClaw.topWristTransferPos();
        outtakeArm.transfer();
        slides.resetSlideEncoders();
        slides.resetProfiler();
        intakeClaw.clawStraight();
            }
    public void resetTeleop(){
        intakeArm.armToUpPos();
        intakeClaw.closeClaw();
        outtakeClaw.outtakeClawClose();
        intakeClaw.wristToIntakePos();
        outtakeClaw.topWristTransferPos();
        intakeClaw.clawStraight();
    }
    public void prepAuto() {
        intakeArm.armToStorage();
        outtakeArm.outtake();
        intakeClaw.openClaw();
        outtakeClaw.outtakeClawClose();
        intakeClaw.wristToIntakePos();
        outtakeClaw.wristIns();
    }
    public void resetEncoders() {
        fl.setMode(STOP_AND_RESET_ENCODER);
        fr.setMode(STOP_AND_RESET_ENCODER);
        br.setMode(STOP_AND_RESET_ENCODER);
        bl.setMode(STOP_AND_RESET_ENCODER);
    }


    public Action actionTransfer() {
        return new SequentialAction(
                new InstantAction(() -> intakeClaw.closeClaw()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeArm.transfer()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(1),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.wristToIntakePos())
        );
    }

    public Action intakeAuto() {
        return new SequentialAction(
                new InstantAction(() -> intakeArm.intake()),
                new InstantAction(() -> outtakeArm.transfer()),
                new InstantAction(() -> intakeClaw.openClaw()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new SleepAction(0.8),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(0.4),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeArm.transfer()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(1),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.4),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.4),
                new InstantAction(() -> intakeClaw.wristToIntakePos())
        );
    }
    public Action autolastsample() {
        return new SequentialAction(
                new InstantAction(() -> intakeArm.intake()),
                new InstantAction(() -> outtakeArm.transfer()),
                new InstantAction(() -> intakeClaw.openClaw()),
                new InstantAction(() -> intakeClaw.clawSlanted()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new SleepAction(0.8),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(0.4),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeArm.transfer()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(1),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.4),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.4),
                new InstantAction(() -> intakeClaw.wristToIntakePos())
        );
    }

    public Action armFlip(){
        return new SequentialAction(
                new InstantAction(()->outtakeArm.transfer()),
                new InstantAction(()-> intakeArm.armToUpPos())
        );
    }

    public Action actionHighBucket() {
        return new SequentialAction(
                new InstantAction(() -> slides.runToTopBucket()),
                new SleepAction(1.3),
                new InstantAction(()-> outtakeArm.outtake()),
                new InstantAction(() -> outtakeClaw.topWristToOuttakePos())
        );
    }

    public Action actionBucketDrop() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new SleepAction(.5),
                new InstantAction(()-> outtakeArm.transfer()),
                new SleepAction(0.15),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage())

        );
    }

    public Action actionRelease(){
        return new SequentialAction(
        new InstantAction(()-> outtakeClaw.outtakeClawOpen()),
        new InstantAction(()-> outtakeArm.transfer())
        );
    }

    public Action actionSlidesLower() {
        return new  SequentialAction(
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(()-> outtakeClaw.topWristTransferPos())
    );
    }
    public Action toIntake() {
        return new SequentialAction(
                new InstantAction(() -> intakeArm.intake()),
                new InstantAction(()-> intakeClaw.wristToIntakePos()),
                new InstantAction(()-> intakeClaw.openClaw())
        );
    }
    public Action actionHighChamber(){
        return new SequentialAction(
            new InstantAction(()-> outtakeArm.transfer()),
            new InstantAction(()-> outtakeClaw.topWristToOuttakePos())
            //new InstantAction(()-> slides.runToHighChamber())
        );
    }
    public Action actionIntakeSpecimen(){
        return new SequentialAction(
                new InstantAction(()-> outtakeArm.outtake()),
                new InstantAction(()-> outtakeClaw.topWristToOuttakePos()),
                new InstantAction(()->outtakeClaw.outtakeClawOpen())
        );
    }
    public Action autoSpecimen() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> outtakeArm.outtake()),
                new InstantAction(() -> outtakeClaw.topWristToOuttakePos()),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.5)
        );
    }

    public Action actionIntakeSample(){
        return new SequentialAction(
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeArm.intake()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new InstantAction(() -> intakeArm.Hover())


                );
            }





    public class actionPeriodic implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            slides.periodic();
            return true;
        }
    }

    public Action actionPeriodic() {
        return new actionPeriodic();
    }
}

