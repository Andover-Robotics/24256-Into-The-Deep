package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeArm {
    public Servo bucketServoR, bucketServoL;
    public static final double transfer = .227;
    public static final double outtake = .745;
    public static final double outtakeAuto = .755;
    public static final double tall = .265;
    public static final double verticalish = .5;
    public static final double wallIntake = .98;
    public static final double park = .285;

    public OuttakeArm(OpMode opMode){
        bucketServoR = opMode.hardwareMap.get(Servo.class, "bucketServoR");
        bucketServoL = opMode.hardwareMap.get(Servo.class, "bucketServoL");
    }
    public void transfer(){
        bucketServoR.setPosition(transfer);
        bucketServoL.setPosition(transfer);
    }
    public void tall(){
        bucketServoR.setPosition(tall);
        bucketServoL.setPosition(tall);
    }
    public void outtake(){
        bucketServoR.setPosition(outtake);
        bucketServoL.setPosition(outtake);
    }

    public void outtakeAuto(){
        bucketServoR.setPosition(outtakeAuto);
        bucketServoL.setPosition(outtakeAuto);
    }
    public void vertical(){
        bucketServoR.setPosition(verticalish);
        bucketServoL.setPosition(verticalish);
    }
    public void wallIntake(){
        bucketServoR.setPosition(wallIntake);
        bucketServoL.setPosition(wallIntake);
    }
    public void park(){
        bucketServoR.setPosition(park);
        bucketServoL.setPosition(park);
    }
}
