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
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0, Math.toRadians(-90)));

        Action runAuto = drive.actionBuilder(new Pose2d(0,0, Math.toRadians(-90)))
                .strafeToConstantHeading(new Vector2d(0, 5))
                .build();

        waitForStart();
        Actions.runBlocking(
                runAuto
        );
    }
}