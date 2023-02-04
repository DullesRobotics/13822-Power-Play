package org.firstinspires.ftc.teamcode.Fenrir;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;

public class AutonomousFunctions {
    private static volatile MecanumDriveTrain mainFrame;
    public static int timeToDriveFar = 6200, timeToDriveNear = 1000;
    public static int timeToStrafeNear = 1325, timeToStrafeFar = 1000;
    public static int timeToLIFT = 1600, correctionTime = 250, timeToSpin = 4000, timeToStrafe = 500;

    public static void parkTime(LinearOpMode op, TeamColor t, FieldPosition position) {
        mainFrame = new MecanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if (op.isStopRequested()) return;
        long timeToDrive = position == FieldPosition.LONG ? timeToDriveFar + timeToStrafeNear : timeToStrafeNear;
        timeToDrive += System.currentTimeMillis();
        while (System.currentTimeMillis() < timeToDrive && op.opModeIsActive())
            if (t == TeamColor.RED && position == FieldPosition.SHORT) {
                //If team is red and on short side strafe left a short distance
                mainFrame.autoStrafeTimed(timeToStrafeNear, true);
            }
            else if (t == TeamColor.RED && position == FieldPosition.LONG) {
                //If team is red and on long side strafe right a short distance
                //Then drive forward a long distance
                mainFrame.autoStrafeTimed(timeToStrafeNear, false);
                mainFrame.setIndividualDrivePower(-0.3, -0.3, -0.3, -0.3);
            }
            else if (t == TeamColor.BLUE && position == FieldPosition.SHORT) {
                //If team is blue and on short side strafe right a short distance
                mainFrame.autoStrafeTimed(timeToStrafeNear, false);
            }
            else if (t == TeamColor.BLUE && position == FieldPosition.LONG) {
                //If team is blue and on long side strafe left a short distance
                //Then drive forward a long distance
                mainFrame.autoStrafeTimed(timeToStrafeNear, true);
                mainFrame.setIndividualDrivePower(-0.3,-0.3,-0.3,-0.3);
            }
        mainFrame.setSidedDrivePower(0, 0);
        op.requestOpModeStop();
    }
    public static void startNew(LinearOpMode op, TeamColor t, FieldPosition position) {
        mainFrame = new MecanumDriveTrain(op);
        mainFrame.addHardware(Configurator.getHardware(mainFrame));
        op.waitForStart();
        if (op.isStopRequested()) return;

    }
        public enum TeamColor{
            RED, BLUE
        }

        public enum Direction {
            RIGHT, LEFT
        }

        public enum FieldPosition{
            LONG, SHORT, TERMINAL
        }
}
