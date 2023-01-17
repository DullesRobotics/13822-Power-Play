package org.firstinspires.ftc.teamcode.Fenrir.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.RobotManager.StandardDriveTrain;
import org.firstinspires.ftc.teamcode.Fenrir.Configurator;
//import org.firstinspires.ftc.teamcode.Tyre.ControlCenterTeleOp;

@TeleOp
public class MasterTeleOp extends LinearOpMode {
    private MecanumDriveTrain baseRobot;
    @Override
    public void runOpMode() throws InterruptedException {
        baseRobot = new MecanumDriveTrain(this);
        baseRobot.addHardware(Configurator.getHardware(baseRobot));

        waitForStart();

        baseRobot.driveWithController(baseRobot.ctrl1());


        while (opModeIsActive())
            baseRobot.getLogger().updateLog();

        baseRobot.stopAllThreads();
    }
}