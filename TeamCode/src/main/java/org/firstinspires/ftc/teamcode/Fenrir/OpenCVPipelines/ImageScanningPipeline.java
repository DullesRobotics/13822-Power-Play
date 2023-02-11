package org.firstinspires.ftc.teamcode.Fenrir.OpenCVPipelines;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Libraries.AddOns.EasyOpenCV;
import org.firstinspires.ftc.teamcode.Libraries.AddOns.Pipeline;
import org.firstinspires.ftc.teamcode.Libraries.Logger;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Config
public class ImageScanningPipeline extends OpenCvPipeline implements Pipeline{

    Mat dst = new Mat();

    MatOfRect ducksFound = new MatOfRect();

    private Position thisPosition;
    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, dst, Imgproc.COLOR_RGB2GRAY);
        Imgproc.rectangle(input, new Rect(new Point(0,100),new Point(200,EasyOpenCV.VIEWPORT_HEIGHT)),new Scalar(255,255,255));
        return input;
    }

    private Position coneMarker(Mat input){
        Position position;

        double numerator = 0;
        double denominator = 0;
        for(int x = 0; x < EasyOpenCV.VIEWPORT_WIDTH; x+=2){
            denominator++;
        }

        CascadeClassifier duckFinder = new CascadeClassifier();
        duckFinder.load("~/capstone_image.xml");

        duckFinder.detectMultiScale(input, ducksFound, 1.5);

        Rect[] ducksInCapstone = ducksFound.toArray();
        for(Rect duck : ducksInCapstone){
            Imgproc.rectangle(input, duck.tl(), duck.br(),new Scalar(0,255,0));
        }

        return position;
    }

    @Override
    public void updateLog(Logger l) {
//        l.putData("Cone Side: ", );
    }

    enum Position{
        POS1,
        POS2,
        POS3;
    }

}
