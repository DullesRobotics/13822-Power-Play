package org.firstinspires.ftc.teamcode.Fenrir.OpModes.AutonomousOpModes;

import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.FieldPosition.SHORT;
import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.TeamColor.BLUE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions;


@Autonomous
@Config
public class BlueParkNearAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        AutonomousFunctions.parkTime(this, BLUE, SHORT);
    }
}
