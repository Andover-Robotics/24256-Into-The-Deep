package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeArm {
    public Servo bucketServoR, bucketServoL;
    public static final double afterTransfer1Pos = 0;
    public static final double transfer2Pos = 1;
    public OuttakeArm(OpMode opMode){
        bucketServoR = opMode.hardwareMap.get(Servo.class, "bucketServoR");
        bucketServoL = opMode.hardwareMap.get(Servo.class, "bucketServoL");
    }
    public void transfer(){
        bucketServoR.setPosition(afterTransfer1Pos);
        bucketServoL.setPosition(afterTransfer1Pos);
    }
    public void outtake(){
        bucketServoR.setPosition(transfer2Pos);
        bucketServoL.setPosition(transfer2Pos);
    }
}
