package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    public Servo  claw, wristServo, rotateServo;

    public static final double clawOpen = 0.15;
    public static final double clawClose = 0.47;
    public static final double wristUp = 0.223 ;
    public static final double wristDown = 0.72;
    public static final double rotateStraight = .51;
    public static final double rotateSide = 1;
    public static final double rotate45Deg = .25;


    public boolean open = true;
    public enum clawOrientation{
        STRAIGHT,
        HORIZONTAL,
        SLANTED
    }
    clawOrientation orientation = clawOrientation.STRAIGHT;

    public IntakeClaw(OpMode opMode) {
        claw = opMode.hardwareMap.get(Servo.class,"claw");
        wristServo = opMode.hardwareMap.get(Servo.class,"wristServo");
        rotateServo = opMode.hardwareMap.get(Servo.class,"rotationServo");
    }

    public void toggleClaw() {
        if (open) {
            closeClaw();
            open = false;
        } else {
            openClaw();
            open = true;
        }
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

    public void clawStraight() {
        rotateServo.setPosition(rotateStraight);
        orientation = clawOrientation.STRAIGHT;
    }
    public void clawSlanted(){
        rotateServo.setPosition(rotate45Deg);
        orientation = clawOrientation.SLANTED;
    }
    public void clawHorizontal(){
        rotateServo.setPosition(rotateSide);
        orientation = clawOrientation.HORIZONTAL;
    }
    public clawOrientation getOrientation(){
        return orientation;
    }




}
