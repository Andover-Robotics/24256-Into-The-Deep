package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArm {
    public Servo armServoR, armServoL;
    public static final double transferPos = 0.39;
    public static final double intakePos = 0.29;
    public static final double ArmUpPos = 0.34;


    public IntakeArm(OpMode opMode) {
        armServoR = opMode.hardwareMap.get(Servo.class, "armServoR");
        armServoL = opMode.hardwareMap.get(Servo.class, "armServoL");
    }
    public void armToTransferPos () {
        armServoL.setPosition(transferPos);
        armServoR.setPosition(transferPos);
    }
    public void armToIntakePos () {
        armServoL.setPosition(intakePos);
        armServoR.setPosition(intakePos);
    }
    public void armToUpPos(){
        armServoL.setPosition(ArmUpPos);
        armServoR.setPosition(ArmUpPos);
    }
}
