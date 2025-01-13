package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeArm {
    public Servo bucketServoR, bucketServoL;
    public static final double afterTransfer1Pos = 0.38;
    public static final double transfer2Pos = 0.9;
    public OuttakeArm(OpMode opMode){
        bucketServoR = opMode.hardwareMap.get(Servo.class, "bucketServoR");
        bucketServoL = opMode.hardwareMap.get(Servo.class, "bucketServoL");
    }
    public void armToAfterTransfer1Pos(){
        bucketServoR.setPosition(afterTransfer1Pos);
        bucketServoL.setPosition(afterTransfer1Pos);
    }
    public void armToTransfer2Pos(){
        bucketServoR.setPosition(transfer2Pos);
        bucketServoL.setPosition(transfer2Pos);
    }
}
