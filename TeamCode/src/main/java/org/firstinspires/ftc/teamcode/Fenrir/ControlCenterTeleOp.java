package org.firstinspires.ftc.teamcode.Fenrir;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

import java.util.logging.Level;

@Config
public class ControlCenterTeleOp {

    public static double liftUpModifier = -0.8, liftDownModifier = 0.8, liftDefaultPower = -0.07;
    public static double clawClosedPosition = 0.3, clawGripPosition = 0.2, clawOpenPosition = 0.5;
    public static double liftHighTime = 2500, liftMidTime = 1000, liftLowTime = 500;

    public static void intakeUpDown(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor leftLiftMotor = r.getMotor("VS");
            //Motor rightLiftMotor = r.getMotor("RL");
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){ //DOWN
                    leftLiftMotor.get().setPower(ctrl.leftTrigger() * liftDownModifier);
                    //rightLiftMotor.get().setPower(ctrl.leftTrigger() * liftDownModifier * -1);
                } else if(ctrl.rightTrigger() > 0) { //UP
                    leftLiftMotor.get().setPower(-ctrl.rightTrigger() * liftUpModifier * -1);
                    //rightLiftMotor.get().setPower(-ctrl.rightTrigger() * liftUpModifier);
                } else {
                    leftLiftMotor.get().setPower(liftDefaultPower);
                    //rightLiftMotor.get().setPower(liftDefaultPower * -1);
                }
            }
        }), true);
    }

    public static void liftLocation(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor liftMotor = r.getMotor("VS");
            boolean isOn = false, up = false, togglePressedHigh = false, togglePressedMiddle = false, togglePressedLow = false;
            long current = System.currentTimeMillis();
            while(r.op().opModeIsActive()){
                r.getLogger().putData("Intake Status", isOn ? up ? "Up" : "Down" : "Hover");

                if(togglePressedHigh && !ctrl.buttonX() && System.currentTimeMillis() > (System.currentTimeMillis()+liftHighTime))
                    togglePressedHigh = false;

                if(togglePressedMiddle && !ctrl.buttonY())
                    togglePressedMiddle = false;

                if(togglePressedLow && !ctrl.buttonUp())
                    togglePressedLow = false;


                if(ctrl.buttonX()){
                    liftMotor.get().setPower(-0.8);
                    liftMotor.get().setPower(liftDefaultPower);
                } else if (ctrl.buttonY()) {
                    while (System.currentTimeMillis()<liftMidTime)
                        liftMotor.get().setPower(-0.8);
                    liftMotor.get().setPower(liftDefaultPower);
                } else if (ctrl.buttonUp()) {
                    while (System.currentTimeMillis()<liftLowTime)
                        liftMotor.get().setPower(-0.8);
                    liftMotor.get().setPower(liftDefaultPower);
                } else if (ctrl.buttonDown()){
                    liftMotor.get().setPower(1);
                }
            }
        }), true);
    }

    public static void clawRelease(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Servo outtakeServo = r.getServo("CLAW");
            outtakeServo.get().setPosition(clawOpenPosition);
            while (r.op().opModeIsActive()) {
                if(ctrl.rightBumper())
                    outtakeServo.get().setPosition(clawClosedPosition);
                else if(ctrl.leftBumper()){
                    outtakeServo.get().setPosition(clawGripPosition);
                } else {
                    outtakeServo.get().setPosition(clawOpenPosition);
                }
            }
        }), true);
    }

}
