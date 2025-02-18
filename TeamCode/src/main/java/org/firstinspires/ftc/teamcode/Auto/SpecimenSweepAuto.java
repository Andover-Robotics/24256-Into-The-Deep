package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

@Autonomous(name = "Specimen Sweep Auto")
public class SpecimenSweepAuto extends LinearOpMode {
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
                .stopAndAdd(bot.actionHighChamber())
                .strafeToLinearHeading(new Vector2d(6, 30.5), Math.toRadians(-90))
                .stopAndAdd(bot.actionAutoClip())

                .afterTime(0.01,bot.actionSlidesLower())
                //clip preload
                .strafeToConstantHeading(new Vector2d(-20,40))
                .afterTime(0.01,bot.actionSweep())
                .splineToLinearHeading(new Pose2d(-42, 39,Math.toRadians(-120)),Math.toRadians(55))
                .strafeToLinearHeading(new Vector2d(-44,64),Math.toRadians(140))
                .stopAndAdd(bot.armUp())
                .afterTime(1,bot.actionSweep())
                .strafeToLinearHeading(new Vector2d(-52,34),Math.toRadians(-120))
                .afterTime(0.1, bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-53.5,62),Math.toRadians(140))
                .strafeToLinearHeading(new Vector2d(-38,57),Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-38,67.5),Math.toRadians(-90))




                //push specimen into obs zone

                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(2,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(2,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //second specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-32, 56), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-1,45),drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-1,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //third specimen

                .afterTime(0.4,bot.autoSpecimen())
                .strafeToLinearHeading(new Vector2d(-38, 58), Math.toRadians(-90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-38,67.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.autoIntakeSpecimen())
                .stopAndAdd(bot.actionHighChamber())
                .strafeToConstantHeading(new Vector2d(-4,45), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .splineToConstantHeading(new Vector2d(-4,30.5),Math.toRadians(90), drive.defaultVelConstraint, new ProfileAccelConstraint(-35,80))
                .stopAndAdd(bot.actionAutoClip())
                .stopAndAdd(bot.actionSlidesLower())
                //fourth specimen

                .strafeToConstantHeading(new Vector2d(-37, 60))
                .afterTime(0.001,bot.toIntake())
                //park*/

                .build();
        waitForStart();
        Actions.runBlocking(
                new ActionHelper.RaceParallelCommand(
                        bot.actionPeriodic(),
                        runAuto
                ));

    }
}





