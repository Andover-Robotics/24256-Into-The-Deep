package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeClaw {
    public Servo topClaw, topWrist;
    public static final double topClawOpen = 0.1;
    public static final double topClawClose = 0.575;
    public static final double topWristUp = 0.46;
    public static final double topWristDown = 0.8;
    public static double topWristIns = 0.7;
    public static double horizontalish = 0;
    public boolean open = false;
    public static final double topClawVertical = .9;
    public void toggleTopClaw() {
        if (open) {
            outtakeClawClose();
            open = false;
        } else {
            outtakeClawOpen();
            open = true;
        }
    }

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
    public void wristIns(){
        topWrist.setPosition(topWristIns);
    }
    public void outtakeClawVertical(){
        topWrist.setPosition(topClawVertical);
    }
    public void moreHorizontalish(){
        topWrist.setPosition(horizontalish);
    }
}
