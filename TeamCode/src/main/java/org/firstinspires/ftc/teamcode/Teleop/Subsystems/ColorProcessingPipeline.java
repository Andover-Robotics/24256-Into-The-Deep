package org.firstinspires.ftc.teamcode.Teleop.Subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorProcessingPipeline extends OpenCvPipeline {
    private  Mat matYCrCb = new Mat();
    
    @Override
    public Mat processFrame(Mat input){
        // converts to YCrCb
            Imgproc.cvtColor(input, matYCrCb, Imgproc.COLOR_RGB2YCrCb);


    {
        return input;
    }
}
}
