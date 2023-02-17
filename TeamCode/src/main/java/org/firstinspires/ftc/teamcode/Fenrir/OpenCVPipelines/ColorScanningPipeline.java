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
public class ColorScanningPipeline extends OpenCvPipeline implements Pipeline{

    public Color colorArea;

    public Mat processFrame(Mat input){
        Imgproc.rectangle(input, new Rect(new Point(0,0), new Point(200, EasyOpenCV.VIEWPORT_HEIGHT)), new Scalar(124, 255, 0), 5);
        colorArea = detectColor(input);
        return input;
    }

    private Color detectColor(Mat input){
        Color finalColor;

        double[] numerators = new double[]{0,0,0};
        double denominator = 0;

        for(int x = 0; x < EasyOpenCV.VIEWPORT_WIDTH-1; x+=2){
            for(int y = 0; y < EasyOpenCV.VIEWPORT_HEIGHT-1; y+=2) {
                denominator++;

                double[] rgb = input.get(y, x);
                if (rgb[0] < 150 && rgb[1] > 180 && rgb[2] < 150)
                    numerators[0]++;
                else if(rgb[0] > 150 && rgb[1] < 50 && rgb[2]> 100)
                    numerators[1]++;
                else if(rgb[0] < 70 && rgb[1] > 130 && rgb[2] > 170)
                    numerators[2]++;
            }
        }

        double[] averages = new double[]{ numerators[0] / denominator, numerators[1] / denominator, numerators[2] / denominator };
        double highestAvg = Math.max(averages[0], Math.max(averages[1], averages[2]));
        if(highestAvg == averages[0]) {
            finalColor = Color.GREEN;
        }
        else if(highestAvg == averages[1]) {
            finalColor = Color.PURPLE;
        }
        else {
            finalColor = Color.BLUE;
        }


        return finalColor;
    }

    @Override
    public void updateLog(Logger l) {
//        l.putData("Cone Side: ", Color);
    }

//    public enum Position {
//        POS(0,
//            0,
//            0);
//
//        private int COLOR_X, COLOR_Y, COLOR_Z;
//
//        Position(int COLOR_X, int COLOR_Y, int COLOR_Z) {
//            this.COLOR_X = COLOR_X;
//            this.COLOR_Y = COLOR_Y;
//            this.COLOR_Z = COLOR_Z;
//        }
//    }

    public enum Color{
        PURPLE,
        GREEN,
        BLUE
    }

}
