package org.firstinspires.ftc.teamcode.Fenrir;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.Hardware.Controller;
import org.firstinspires.ftc.teamcode.Hardware.Motor.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Servo;
import org.firstinspires.ftc.teamcode.RobotManager.Robot;

@Config
public class ControlCenterTeleOp {

    public static double liftUpModifier = 0.6, liftDownModifier = -0.4, liftDefaultPower = 0.1;

    public static void intakeUpDown(Robot r, Controller ctrl){
        r.addThread(new Thread(() -> {
            Motor leftLiftMotor = r.getMotor("LL");
            Motor rightLiftMotor = r.getMotor("RL");
            while(r.op().opModeIsActive()){
                if(ctrl.leftTrigger() > 0){ //DOWN
                    leftLiftMotor.get().setPower(ctrl.leftTrigger() * liftDownModifier);
                    rightLiftMotor.get().setPower(ctrl.leftTrigger() * liftDownModifier * -1);
                } else if(ctrl.rightTrigger() > 0) { //UP
                    leftLiftMotor.get().setPower(-ctrl.rightTrigger() * liftUpModifier * -1);
                    rightLiftMotor.get().setPower(-ctrl.rightTrigger() * liftUpModifier);
                } else {
                    leftLiftMotor.get().setPower(liftDefaultPower);
                    rightLiftMotor.get().setPower(liftDefaultPower * -1);
                }
            }
        }), true);
    }

}
