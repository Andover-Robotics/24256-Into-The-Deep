package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArm {
    public Servo armServoR, armServoL;
    public static final double transferPos = 0.39;
    public static final double intakePos = 0.19;
    public static final double ArmUpPos = 0.47;
    public static final double ArmHoverPos = 0.27;
    public final double armStorage = 0.7;

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
    public void Hover (){
        armServoL.setPosition(ArmHoverPos);
        armServoR.setPosition(ArmHoverPos);
    }
}
