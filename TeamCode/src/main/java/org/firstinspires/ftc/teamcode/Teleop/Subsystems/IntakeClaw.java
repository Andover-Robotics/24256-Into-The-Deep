package org.firstinspires.ftc.teamcode.Teleop.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {
    public Servo  claw, wristServo, rotateServo;

    public static final double clawOpen = 0.180;
    public static final double clawClose = 0.528;
    public static final double clawLoose = 0.47;
    public static final double transfer = 0;
    public static final double intake = 0.71;
    public static final double rotateStraight = .34;
    public static final double rotateSide = .7;
    public static final double rotate45Deg = .535;
    public static final double rotateOther45Deg =.2;
    public static final double wristLegal = 0.1;


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
        wristServo.setPosition(transfer);
    }
    public void wristToIntakePos(){
        wristServo.setPosition(intake);
    }
    public void setWristLegal(){
        wristServo.setPosition(wristLegal);
    }
    public void clawLoose (){
        claw.setPosition(clawLoose);
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

    public void rotate0ther45Deg(){
        rotateServo.setPosition((rotateOther45Deg));
        orientation = clawOrientation.OTHERSLANTED;

    }

    public clawOrientation getOrientation(){
        return orientation;
    }




}
