package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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

    public MotorEx bl, br, fl, fr;
    public boolean fieldCentricRunMode = false;

    public Bot(OpMode opMode) {
        this.opMode = opMode;

        try {
            fieldCentricRunMode = false;
        } catch (Exception e) {
            fieldCentricRunMode = false;
        }
        fl = new MotorEx(opMode.hardwareMap, "fl", Motor.GoBILDA.RPM_435);
        fr = new MotorEx(opMode.hardwareMap, "fr", Motor.GoBILDA.RPM_435);
        bl = new MotorEx(opMode.hardwareMap, "bl", Motor.GoBILDA.RPM_435);
        br = new MotorEx(opMode.hardwareMap, "br", Motor.GoBILDA.RPM_435);

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
        fl.set(speeds[0]);
        fr.set(speeds[1]);
        bl.set(-speeds[2]);
        br.set(-speeds[3]);
    }
    public enum BotStates{
        STORAGE,
        INTAKE_SPEC,
        INTAKE_SAM,
        CLIP,
        CLIP_POS,
        BUCKET,
        TRANSFER,
    }
    public BotStates state = BotStates.STORAGE; //bot state at init

    public void prepMotors() {

        fl.setInverted(true);
        bl.setInverted(true);
        fl.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);

        fl.setRunMode(Motor.RunMode.RawPower);
        fr.setRunMode(Motor.RunMode.RawPower);
        bl.setRunMode(Motor.RunMode.RawPower);
        br.setRunMode(Motor.RunMode.RawPower);

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
        outtakeArm.transfer();
        intakeArm.Hover();
        intakeClaw.openClaw();
        outtakeClaw.outtakeClawOpen();
        intakeClaw.wristToIntakePos();
        outtakeClaw.topWristTransferPos();
        slides.resetSlideEncoders();
        slides.resetProfiler();
        intakeClaw.clawStraight();
            }
    public void resetTeleop(){
        intakeArm.Hover();
        intakeClaw.closeClaw();
        outtakeClaw.outtakeClawClose();
        intakeClaw.wristToIntakePos();
        outtakeClaw.topWristTransferPos();
        intakeClaw.clawStraight();
        slides.runToStorage();
        outtakeArm.transfer();
        state = BotStates.STORAGE;
    }
    public void prepAuto() {
        intakeArm.armToStorage();
        outtakeArm.wallIntake();
        intakeClaw.closeClaw();
        outtakeClaw.outtakeClawClose();
        intakeClaw.setWristLegal();
        outtakeClaw.setTopWrist18();
        intakeClaw.clawStraight();
    }
    //prep methods
    public Action actionSlidesLower() {
        return new  SequentialAction(
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(()-> outtakeClaw.topWristTransferPos())
        );
    }

    public Action toIntake() {
        return new SequentialAction(
                new InstantAction(() -> intakeArm.Hover()),
                new InstantAction(()-> intakeClaw.wristToIntakePos()),
                new InstantAction(()-> intakeClaw.openClaw())
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
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(()-> outtakeArm.transfer()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(0.65),
                new InstantAction(() -> intakeArm.transfer()),
                new InstantAction(() -> intakeClaw.clawLoose()),
                new SleepAction(.25),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(.2),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.25),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(()-> intakeArm.Hover())
        );
    }
    public Action actionBucketDropAuto() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new SleepAction(.4),
                new InstantAction(()-> outtakeArm.vertical()),
                new SleepAction(0.15),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new SleepAction(0.8),
                new InstantAction(()-> outtakeArm.transfer()),
                new SleepAction(0.1),
                new InstantAction(()-> slides.resetSlideEncoders())

        );
    }
    public Action actionHighChamberAuto(){
        return new SequentialAction(
                new InstantAction(()-> outtakeClaw.outtakeClawVertical()),
                new InstantAction(()-> outtakeArm.vertical()),
                new InstantAction(()->intakeArm.armToStorage()),
                new InstantAction(()-> slides.runToPushAuto())

        );
    }

    public Action actionAutoClip(){
        return new SequentialAction(
                new InstantAction(()-> slides.runToHighChamber()),
                new SleepAction(0.6),
                new InstantAction (()-> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(()-> outtakeClaw.topWristTransferPos())
        );
    }

    public Action autoSpecimen() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> outtakeArm.wallIntake()),
                new InstantAction(() -> outtakeClaw.wristToWall())
        );
    }
    public Action autoIntakeSpecimen() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawClose())
        );
    }
    public Action actionSweep(){
        return new SequentialAction(
                new InstantAction(()-> intakeClaw.wristToTransferPos()),
                new SleepAction(0.5),
                new InstantAction(()->intakeArm.armSweep())

        );
    }
    public Action armUp(){
        return new SequentialAction(
                new InstantAction(()->intakeArm.Hover())
        );
    }


    public Action autoLastSample() {
        return new SequentialAction(
                new InstantAction(() -> intakeClaw.rotate0ther45Deg()),
                new InstantAction(() -> intakeArm.intake()),
                new InstantAction(() -> outtakeArm.transfer()),
                new InstantAction(() -> intakeClaw.openClaw()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.clawStraight()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(()-> outtakeArm.transfer()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(0.75),
                new InstantAction(() -> intakeArm.transfer()),
                new InstantAction(() -> intakeClaw.clawLoose()),
                new SleepAction(.3),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(.2),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(()-> intakeArm.Hover())
        );
    }
    public Action actionPark(){
        return new SequentialAction(
                new InstantAction(()-> outtakeClaw.outtakeClawClose()),
                new InstantAction(()-> outtakeClaw.topWristPark())
        );
    }
    public Action savingBottomWrist() {
        return new SequentialAction(
                new InstantAction(()->intakeClaw.wristToIntakePos())
        );
    }
    public Action iDontLikeThis() {
        return new SequentialAction(
                new InstantAction(()->intakeClaw.clawHorizontal())
        );
    }
    public Action iDontLikeThisPart2() {
        return new SequentialAction(
                new InstantAction(()->intakeClaw.clawHorizontalBruh())
        );
    }
    //auto methods primarily

    public Action actionTransfer() {
        return new SequentialAction(
                new InstantAction(() -> intakeClaw.clawStraight()),
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new InstantAction(()-> outtakeArm.transfer()),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new InstantAction(() -> intakeClaw.wristToTransferPos()),
                new SleepAction(0.7),
                new InstantAction(() -> intakeArm.transfer()),
                new InstantAction(() -> intakeClaw.clawLoose()),
                new SleepAction(.3),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(.2),
                new InstantAction(() -> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeClaw.wristToIntakePos()),
                new InstantAction(()-> intakeArm.Hover()),
                new InstantAction(()-> state = BotStates.TRANSFER)
        );
    }

    public Action actionHighBucket() {
        return new SequentialAction(
                new InstantAction(()-> intakeArm.Hover()),
                new InstantAction(() -> slides.runToTopBucket()),
                new InstantAction(()-> outtakeArm.vertical()),
                new SleepAction(0.9),
                new InstantAction(()-> outtakeArm.outtake()),
                new InstantAction(() -> outtakeClaw.topWristToOuttakePos()),
                new InstantAction(()-> state = BotStates.BUCKET)
        );
    }
    public Action actionHighBucketAuto() {
        return new SequentialAction(
                new InstantAction(()-> intakeArm.Hover()),
                new InstantAction(() -> slides.runToTopBucket()),
                new InstantAction(()-> outtakeArm.vertical()),
                new SleepAction(0.9),
                new InstantAction(()-> outtakeArm.outtakeAuto()),
                new InstantAction(() -> outtakeClaw.topWristToOuttakePos()),
                new InstantAction(()-> state = BotStates.BUCKET)
        );
    }
    public Action actionLowBucket() {
        return new SequentialAction(
                new InstantAction(()-> intakeArm.Hover()),
                new InstantAction(() -> slides.runToLowBucket()),
                new InstantAction(()-> outtakeArm.vertical()),
                new SleepAction(0.6),
                new InstantAction(()-> outtakeArm.outtake()),
                new InstantAction(() -> outtakeClaw.topWristToOuttakePos()),
                new InstantAction(()-> state = BotStates.BUCKET)
        );
    }

    public Action actionBucketDrop() {
        return new SequentialAction(
                new InstantAction(() -> outtakeClaw.outtakeClawOpen()),
                new SleepAction(.5),
                new InstantAction(()-> outtakeArm.vertical()),
                new SleepAction(0.15),
                new InstantAction(() -> outtakeClaw.topWristTransferPos()),
                new InstantAction(() -> slides.runToStorage()),
                new SleepAction(0.9),
                new InstantAction(()-> outtakeArm.transfer()),
                new SleepAction(0.3),
                new InstantAction(()-> slides.resetSlideEncoders()),
                new InstantAction(()-> state = BotStates.STORAGE)

        );
    }



    public Action actionHighChamber(){
        return new SequentialAction(
                new InstantAction(()->intakeClaw.wristToIntakePos()),
                new InstantAction(()-> outtakeClaw.outtakeClawClose()),
                new SleepAction(0.15),
                new InstantAction(()-> outtakeClaw.outtakeClawVertical()),
                new InstantAction(()-> outtakeArm.vertical()),
                new InstantAction(()->intakeArm.armToStorage()),
                new InstantAction(()-> slides.runToPush()),
                new InstantAction(()-> state = BotStates.CLIP_POS)

        );
    }
    public Action actionClip(){
        return new SequentialAction(
                new InstantAction(()-> slides.runToHighChamber()),
                new InstantAction(()-> state = BotStates.STORAGE)
        );
    }

    public Action actionIntakeSpecimen(){
        return new SequentialAction(
                new InstantAction(()->outtakeClaw.outtakeClawOpen()),
                new SleepAction(0.1),
                new InstantAction(()-> slides.runToStorage()),
                new InstantAction(()-> outtakeArm.wallIntake()),
                new InstantAction(()-> outtakeClaw.wristToWall()),
                new SleepAction(0.65),
                new InstantAction(()-> slides.resetSlideEncoders())
        );
    }




    public Action actionIntakeSample(){
        return new SequentialAction(
                new InstantAction(() -> intakeClaw.openClaw()),
                new SleepAction(0.3),
                new InstantAction(() -> intakeArm.intake()),
                new SleepAction(0.21),
                new InstantAction(() -> intakeClaw.closeClaw()),
                new SleepAction(0.5),
                new InstantAction(() -> intakeArm.Hover()),
                new InstantAction(()-> state = BotStates.INTAKE_SAM)
                );
            }
    public Action actionResetIntake(){
        return new SequentialAction(
                new InstantAction(()-> intakeArm.Hover()),
                new InstantAction(()-> intakeClaw.openClaw()),
                new InstantAction(()-> state = BotStates.STORAGE)
        );
    }
            //teleop methods primarily


    //archaic
    public Action actionRelease(){
        return new SequentialAction(
                new InstantAction(()-> outtakeClaw.outtakeClawOpen()),
                new InstantAction(()-> outtakeArm.transfer())
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

