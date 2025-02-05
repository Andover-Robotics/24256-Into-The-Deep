package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    public Servo  claw, wristServo, rotateServo;

    public static final double clawOpen = 0.15;
    public static final double clawClose = 0.47;
    public static final double wristUp = 0.08 ;
    public static final double wristDown = 0.66;
    public static final double rotateStraight = .523;
    public static final double rotateSide = .85;
    public static final double rotate45Deg = .3;
    public static final double rotateOther45Deg =.65;


    public boolean open = true;
    public enum clawOrientation{
        STRAIGHT,
        HORIZONTAL,
        SLANTED,
        OTHERSLANTED
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

    public void setRotate0ther45Deg(){
        rotateServo.setPosition((rotateOther45Deg));
        orientation = clawOrientation.OTHERSLANTED;

    }

    public clawOrientation getOrientation(){
        return orientation;
    }




}
