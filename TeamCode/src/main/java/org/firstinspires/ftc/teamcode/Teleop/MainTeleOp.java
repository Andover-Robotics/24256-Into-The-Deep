package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import  com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.Bot;
import org.firstinspires.ftc.teamcode.Teleop.Subsystems.IntakeClaw;
import org.firstinspires.ftc.teamcode.Test.IntakeTest;

@TeleOp(name = "Main Teleop")
public class MainTeleOp extends LinearOpMode {
    private GamepadEx gp1,gp2;
    private  boolean toggleTopClaw = false;
    private  boolean toggleBottClaw = false;
    ElapsedTime time = new ElapsedTime();
    Bot bot;
    public void runOpMode() throws InterruptedException{
        bot = Bot.getInstance(this);
        gp1 = new GamepadEx(gamepad1);
        gp2 = new GamepadEx(gamepad2);

        bot.resetEverything();
        waitForStart();
        while (opModeIsActive()) {
            gp2.readButtons();

            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
                toggleBottClaw = !toggleBottClaw;
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
                toggleTopClaw = !toggleTopClaw;
            }
            // opens both bottom and top claws with toggles.
            if (toggleBottClaw) {
                bot.intakeClaw.openClaw();
            } else if (toggleTopClaw) {
                bot.outtakeClaw.outtakeClawOpen();
            } else {
                bot.intakeClaw.closeClaw();
                bot.outtakeClaw.outtakeClawClose();
            }
            if(gp2.wasJustPressed(GamepadKeys.Button.A)){
                bot.goToTransferPos(time);
            } else {
                bot.resetIntake();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.B)){
                bot.goToOuttakePos(time);
            } else {
                bot.resetOuttake();
            }
            if (gp2.wasJustPressed(GamepadKeys.Button.X)){
                bot.intakeArm.armToUpPos();
            } else {
                bot.intakeArm.armToIntakePos();
            }
            drive();

        }



    }
    public void drive() {
        gp1.readButtons();
        bot.prepMotors();
        Vector2d driveVector;
        driveVector = new Vector2d(gp1.getLeftX(),-gp1.getLeftY());
        Vector2d turnVector;
        turnVector = new Vector2d(gp1.getRightX(), 0);
        bot.driveRobotCentric(driveVector.getX()*0.7, driveVector.getY()*0.7, turnVector.getX()/1.7);

    }



}