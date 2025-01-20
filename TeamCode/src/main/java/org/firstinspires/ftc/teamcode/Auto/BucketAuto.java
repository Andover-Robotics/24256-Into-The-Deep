package org.firstinspires.ftc.teamcode.Auto;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Auto.ActionHelper;

@Autonomous(name = "Bucket Auto rr")
public class BucketAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException{
        Bot.instance = null;
        bot = Bot.getInstance(this);
        Pose2d initialPose = new Pose2d(0,0, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Action runAuto = drive.actionBuilder(drive.localizer.getPose())

                //.afterTime(0.01, bot.actionHighBucket())
                //.strafeToConstantHeading(new Vector2d(38, 55))
                .lineToY(10)


//                .waitSeconds(1.5)
//                .stopAndAdd(bot.actionBucketDrop())
//
//                .strafeToLinearHeading(new Vector2d(44, 40), Math.toRadians(-90))



                .build();

//        while(!isStarted()) {
//            bot.slides.periodic();
//        }

        waitForStart();
        Actions.runBlocking(
                runAuto
        );
    }
}
