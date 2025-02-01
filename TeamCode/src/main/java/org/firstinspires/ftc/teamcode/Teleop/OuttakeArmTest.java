package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.storage;
import static org.firstinspires.ftc.teamcode.Teleop.Subsystems.Slides.topBucket;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.OuttakeArm;
import org.firstinspires.ftc.teamcode.Test.IntakeTest;

import java.util.ArrayList;
import java.util.List;

public class OuttakeArmTest extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private  boolean toggleTopClaw = false;
    private  boolean toggleBottClaw = false;
    private boolean liftArm = false;
    private double driveSpeed = 1, driveMultiplier = 1;

    int slidesTarget = 0;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    private List<Action> runningActions = new ArrayList<>();
    private FtcDashboard dash = FtcDashboard.getInstance();


    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Bot.instance = null;
        bot = Bot.getInstance(this);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);
        bot.prepMotors();
        bot.prepSubsystems();
        bot.resetEverything();
        bot.armFlip();
        waitForStart();
        while (opModeIsActive()) {
            gp2.readButtons();
            if (gp2.wasJustPressed(GamepadKeys.Button.A)){
                bot.actionTransfer();
            }
            if(gp2.wasJustPressed(GamepadKeys.Button.B)){
                bot.actionHighBucket();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                bot.actionHighChamber();
            }
        }
    }
}