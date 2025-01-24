package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArm {
    public Servo armServoR, armServoL;
    public static final double transferPos = 0.41;
    public static final double intakePos = 0.23;
    public static final double ArmUpPos = 0.48;
    public final double armStorage = 0.65;


    public IntakeArm(OpMode opMode) {
        armServoR = opMode.hardwareMap.get(Servo.class, "armServoR");
        armServoL = opMode.hardwareMap.get(Servo.class, "armServoL");
    }
    public void transfer () {
        armServoL.setPosition(transferPos);
        armServoR.setPosition(transferPos);
    }
    public void intake () {
        armServoL.setPosition(intakePos);
        armServoR.setPosition(intakePos);
    }
    public void armToUpPos(){
        armServoL.setPosition(ArmUpPos);
        armServoR.setPosition(ArmUpPos);
    }
    public void armToStorage(){
        armServoL.setPosition(armStorage);
        armServoR.setPosition(armStorage);
    }
}
