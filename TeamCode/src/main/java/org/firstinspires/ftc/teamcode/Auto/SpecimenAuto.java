package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;

import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Auto.ActionHelper;
import org.firstinspires.ftc.teamcode.Auto.ActionHelper;
@Autonomous(name = "Specimen Auto ")
public class SpecimenAuto extends LinearOpMode {
    Bot bot;

    @Override
    public void runOpMode() throws InterruptedException{
        Bot.instance = null;
        bot = Bot.getInstance(this);
        bot.prepSubsystems();
        bot.prepAuto();

        Pose2d initialPose = new Pose2d(-20,64, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Action runAuto = drive.actionBuilder(new Pose2d(-20,64, Math.toRadians(-90)))
                .stopAndAdd(bot.//Clip position
                .strafeToLinearHeading(new Vector2d(-20, 35), Math.toRadians(-90))
                //CLIP AND GO TO WALL INTAKE POS
                .strafeToLinearHeading(new Vector2d(-35, 35), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-35, 0), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-45, 0), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(-45, 60), Math.toRadians(-90))
                //PUSHING
                //INTAKE FROM WALL
                .strafeToLinearHeading(new Vector2d(-20, 35), Math.toRadians(-90))
                //CLIP
                .strafeToLinearHeading(new Vector2d(-35, 60), Math.toRadians(-90))
                //INTAKE




