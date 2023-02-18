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
    public static double clawClosedPosition = 0.2, clawGripPosition = 0.15, clawOpenPosition = 0.4;
    public static double liftHighTime = 2500, liftMidTime = 1000, liftLowTime = 500;
    public static double lowerHighTime = 0, lowerMidTime = 0, lowerLowTime = 0;

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
            boolean togglePressedHigh = false, togglePressedMiddle = false, togglePressedLow = false,togglePressedDown = false, isHigh = false, isMiddle = false, isLow = false;
            while(r.op().opModeIsActive()) {
                r.getLogger().putData("Lift Status", togglePressedHigh || togglePressedMiddle || togglePressedLow ? "Raising" : "Hover");

                //Sets each toggle variable == false
                if (togglePressedHigh &&  System.currentTimeMillis() > (System.currentTimeMillis() + liftHighTime)) {
                    togglePressedHigh = false;
                    isHigh = true;
                }
                if (togglePressedMiddle &&  System.currentTimeMillis() > (System.currentTimeMillis() + liftMidTime)) {
                    togglePressedMiddle = false;
                    isMiddle = true;
                }
                if (togglePressedLow &&  System.currentTimeMillis() > (System.currentTimeMillis() + liftLowTime)) {
                    togglePressedLow = false;
                    isLow = true;
                }

                if(isHigh && System.currentTimeMillis() > (System.currentTimeMillis() + lowerHighTime)) {
                    togglePressedDown = false;
                    isHigh = false;
                }
                if(isMiddle && System.currentTimeMillis() > (System.currentTimeMillis() + lowerMidTime)) {
                    togglePressedDown = false;
                    isMiddle = false;
                }
                if(isLow && System.currentTimeMillis() > (System.currentTimeMillis() + lowerLowTime)) {
                    togglePressedDown = false;
                    isLow = false;
                }

                //Sets each toggle == true based on controller input
                if(!togglePressedHigh||!togglePressedMiddle||!togglePressedLow) {
                    if (ctrl.buttonUp())
                        togglePressedHigh = true;
                    if (ctrl.buttonY())
                        togglePressedMiddle = true;
                    if (ctrl.buttonX())
                        togglePressedLow = true;
                }else if (ctrl.buttonDown() && isHigh || isMiddle || isLow)
                    togglePressedDown = true;

                //Sets motor power to up if any toggle booleans == true
                //Sets motor power to down is togglePressedDown boolean == true
                //Sets motor power default if all toggle booleans == false
                if (togglePressedHigh || togglePressedMiddle || togglePressedLow) {
                    liftMotor.get().setPower(liftUpModifier);
                } else if(togglePressedDown) {
                    liftMotor.get().setPower(liftDownModifier);
                }else{
                    liftMotor.get().setPower(liftDefaultPower);
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
