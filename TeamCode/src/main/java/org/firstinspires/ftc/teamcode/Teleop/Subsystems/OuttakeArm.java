package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class OuttakeArm {
    public Servo bucketServoR, bucketServoL;
    public static final double transfer = .195;
    public static final double outtake = .71;
    public static final double vertical = .49;
    public static final double wallIntake = .955;
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
        bucketServoR.setPosition(vertical);
        bucketServoL.setPosition(vertical);
    }
    public void wallIntake(){
        bucketServoR.setPosition(wallIntake);
        bucketServoL.setPosition(wallIntake);
    }
}
