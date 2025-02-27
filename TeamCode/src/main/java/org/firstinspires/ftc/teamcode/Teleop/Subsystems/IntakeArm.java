package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArm {
    public Servo armServoR, armServoL;
    public static final double transferPos = 0.4750;
    public static final double intakePos = 0.24;
    public static final double ArmHoverPos = 0.325;
    public final double armStorage = 0.75;
    public final double sweep = 0.15;

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

    public void armToStorage(){
        armServoL.setPosition(armStorage);
        armServoR.setPosition(armStorage);
    }
    public void Hover (){
        armServoL.setPosition(ArmHoverPos);
        armServoR.setPosition(ArmHoverPos);
    }
    public void armSweep(){
        armServoL.setPosition(sweep);
        armServoR.setPosition(sweep);
    }
}
