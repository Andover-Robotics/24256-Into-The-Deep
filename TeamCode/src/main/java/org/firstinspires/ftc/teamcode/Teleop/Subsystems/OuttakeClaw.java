package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeClaw {
    public boolean open = true;
    public Servo topClaw, topWrist;
    public static final double topClawOpen = 0.10;
    public static final double topClawClose = 0.618;
    public static final double outtake = 0.47;
    public static final double transfer = 0.85;
    public static final double topWrist18 = 0.34;
    public static double topWristIns = 0.71;
    public static final double topClawVertical = .805;
    public static final double wristWall = 0.65;
    public static final double wristPark = 0.24;

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
        topWrist.setPosition(transfer);
    }
    public void topWristPark(){
        topWrist.setPosition(wristPark);
    }

    public void topWristToOuttakePos(){
        topWrist.setPosition(outtake);
    }
    public void wristIns(){
        topWrist.setPosition(topWristIns);
    }
    public void outtakeClawVertical(){
        topWrist.setPosition(topClawVertical);
    }
    public void setTopWrist18() {topWrist.setPosition(topWrist18);}
    public void wristToWall(){
        topWrist.setPosition(wristWall);
    }
}
