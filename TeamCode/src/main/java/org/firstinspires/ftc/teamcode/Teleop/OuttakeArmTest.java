package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Outtake Arm Test")
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
            if (gp2.wasJustPressed(GamepadKeys.Button.X)) {
                bot.actionHighChamber();
            }
                if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                    bot.outtakeClaw.toggleTopClaw();
            }
        }
    }
}