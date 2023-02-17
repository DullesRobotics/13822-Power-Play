package org.firstinspires.ftc.teamcode.Fenrir.OpModes.AutonomousOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.USBWebcam;
import org.firstinspires.ftc.teamcode.Libraries.AddOns.EasyOpenCV;
import org.firstinspires.ftc.teamcode.Fenrir.Configurator;
import org.firstinspires.ftc.teamcode.RobotManager.MecanumDriveTrain;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.firstinspires.ftc.teamcode.Fenrir.OpenCVPipelines.ColorScanningPipeline;

import org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions;
import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.FieldPosition.LONG;
import static org.firstinspires.ftc.teamcode.Fenrir.AutonomousFunctions.TeamColor.RED;

@Autonomous
public class WebcamTest extends LinearOpMode {
    private MecanumDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumDriveTrain(this);

//        robot.addHardware(new USBWebcam(robot, "Webcam"));
//        ColorScanningPipeline pipe = new ColorScanningPipeline();
//        EasyOpenCV ez = new EasyOpenCV(pipe, robot.getUSBWebcam("Webcam"), OpenCvCameraRotation.UPRIGHT);
//        robot.addOnManager().initAddOn(ez);

        AutonomousFunctions.webCamTest(this, RED, LONG);

        waitForStart();

        while (opModeIsActive())
            robot.getLogger().updateLog();

        robot.stopAllThreads();
    }
}
