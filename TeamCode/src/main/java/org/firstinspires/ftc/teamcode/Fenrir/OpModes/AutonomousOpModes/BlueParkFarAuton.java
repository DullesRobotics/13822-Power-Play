package org.firstinspires.ftc.teamcode.Fenrir.OpModes.AutonomousOpModes;

import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.FieldPosition.LONG;
import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.TeamColor.BLUE;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions;

@Autonomous
@Config
public class BlueParkFarAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutonomousFunctions.parkTime(this, BLUE, LONG);
    }
}
