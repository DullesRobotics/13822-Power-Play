package org.firstinspires.ftc.teamcode.Fenrir.OpModes.AutonomousOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;

@Autonomous
public class AutonTest extends LinearOpMode {
    MecanumDriveTrain robot;
    @Override
    public void runOpMode() throws InterruptedException{

        robot = new MecanumDriveTrain(this);

        waitForStart();

    }
}
