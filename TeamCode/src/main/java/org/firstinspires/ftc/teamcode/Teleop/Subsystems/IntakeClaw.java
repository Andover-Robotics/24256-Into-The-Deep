package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class IntakeClaw {
    public Servo  claw, wristServo;

    public static final double clawOpen = 0.15;
    public static final double clawClose = 0.53;
    public static final double wristUp = 0.18;
    public static final double wristDown = 0.67;

    public IntakeClaw(OpMode opMode) {
        claw = opMode.hardwareMap.get(Servo.class,"claw");
        wristServo = opMode.hardwareMap.get(Servo.class,"wristServo");
    }

    public void openClaw(){
        claw.setPosition(clawOpen);
    }
    public void closeClaw(){
        claw.setPosition(clawClose);
    }
    public void wristToTransferPos(){
        wristServo.setPosition(wristUp);
    }
    public void wristToIntakePos(){
        wristServo.setPosition(wristDown);
    }




}
