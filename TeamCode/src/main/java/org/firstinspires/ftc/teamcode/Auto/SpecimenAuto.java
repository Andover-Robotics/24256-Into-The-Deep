package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.tuning.ActionHelper;
import org.firstinspires.ftc.teamcode.Auto.tuning.MecanumDrive;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name = "Specimen Auto")
public class SpecimenAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-24, 64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(-24, 64, Math.toRadians(-90)))
                //clip preloaded
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToLinearHeading(new Vector2d(4, 30.5), Math.toRadians(-90))
                .stopAndAdd(bot.actionAutoClip())

                .afterTime(0.01,bot.actionSlidesLower())
                //clip preload

                //.splineToConstantHeading(new Vector2d(-20,42),90)
                //.strafeToConstantHeading(new Vector2d(-35, 46))
                //.strafeToConstantHeading(new Vector2d(-42,14))
                //.strafeToConstantHeading(new Vector2d(-42,18))

                //.afterTime(0.00001,bot.autoSpecimen())
                //.splineToConstantHeading(new Vector2d(-42, 55),Math.toRadians(90))
                //pushes second sample to obs zone
                //.strafeToConstantHeading(new Vector2d(-38,20))

                .strafeToConstantHeading(new Vector2d(-25, 42))
                .splineToConstantHeading(new Vector2d(-42,30),90)
                .strafeToConstantHeading(new Vector2d(-34, 14))
                .splineToConstantHeading(new Vector2d(-48,18),90)

                .afterTime(0.00001,bot.autoSpecimen())
                .splineToConstantHeading(new Vector2d(-47, 55),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-38,20))


                .splineToConstantHeading(new Vector2d(-57,18),Math.toRadians(130))
                .strafeToLinearHeading(new Vector2d(-57,62),Math.toRadians(-80), drive.defaultVelConstraint, new ProfileAccelConstraint(-20,60))
                .strafeToLinearHeading(new Vector2d(-45,67),Math.toRadians(-95))
                //pushAuto specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-2,45))
                .splineToConstantHeading(new Vector2d(-2,30.5),Math.toRadians(90))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-32, 50), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-1,45))
                .splineToConstantHeading(new Vector2d(-1,30.5),Math.toRadians(90))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 58), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamberAuto())
                .strafeToConstantHeading(new Vector2d(-3,45))
                .splineToConstantHeading(new Vector2d(-3,30.5),Math.toRadians(90))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .strafeToConstantHeading(new Vector2d(-37, 60))
                .afterTime(0.001,bot.toIntake())
                //park

                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





