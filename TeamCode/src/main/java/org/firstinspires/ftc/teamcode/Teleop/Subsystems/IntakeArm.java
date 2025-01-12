package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArm {
    private Servo armServoR, armServoL, claw, wristServo;
    private double transferPos, intakePos, clawOpen, clawClose;

    public IntakeArm(OpMode opMode) {
        armServoR = opMode.hardwareMap.get(Servo.class,"armServoR");
        armServoL = opMode.hardwareMap.get(Servo.class,"armServoL");
        claw = opMode.hardwareMap.get(Servo.class,"claw");
        wristServo = opMode.hardwareMap.get(Servo.class,"wristServo");
    }

}
