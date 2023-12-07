package org.firstinspires.ftc.teamcode.Modules;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

public class VisionController {
    private Robot2023 robot;
    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    private WebcamName webcam;
    private Telemetry telemetry;

    public void onOpmodeInit (Robot2023 robot, WebcamName webcam, Telemetry telemetry) {
        this.robot = robot;
        this.webcam = webcam;
        this.telemetry = telemetry;
    }

    public void doLoop () {

    }
}
