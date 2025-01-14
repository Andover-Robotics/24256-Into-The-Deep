package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeClaw {
    public Servo topClaw, topWrist;
    public static final double topClawOpen = 0.1;
    public static final double topClawClose = 0.6;
    public static final double topWristUp = 0.53;
    public static final double topWristDown = 0.15;

    public OuttakeClaw(OpMode opMode){
    topClaw = opMode.hardwareMap.get(Servo.class, "topClaw");
    topWrist = opMode.hardwareMap.get(Servo.class,"topWrist");
    }
    public void outtakeClawClose(){
        topClaw.setPosition(topClawClose);
    }
    public void outtakeClawOpen(){
        topClaw.setPosition(topClawOpen);
    }
    public void topWristTransferPos(){
        topWrist.setPosition(topWristDown);
    }
    public void topWristToOuttakePos(){
        topWrist.setPosition(topWristUp);
    }
}
