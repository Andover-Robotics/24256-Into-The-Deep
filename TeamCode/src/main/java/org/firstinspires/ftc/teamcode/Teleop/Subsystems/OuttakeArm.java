package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeArm {
    public Servo bucketServoR, bucketServoL;
    public static final double transfer = .215;
    public static final double outtake = .735;
    public static final double verticalish = .467;
    public static final double wallIntake = .975;
    public static final double park = .285;

    public OuttakeArm(OpMode opMode){
        bucketServoR = opMode.hardwareMap.get(Servo.class, "bucketServoR");
        bucketServoL = opMode.hardwareMap.get(Servo.class, "bucketServoL");
    }
    public void transfer(){
        bucketServoR.setPosition(transfer);
        bucketServoL.setPosition(transfer);
    }
    public void outtake(){
        bucketServoR.setPosition(outtake);
        bucketServoL.setPosition(outtake);
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
