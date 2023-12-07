package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrive;

public class Robot2023 {
    LinearOpMode opMode;
    public HardwareMap hardwareMap;
    public Servo clawServo = null;
    public Servo launcherServo = null;
    public DcMotorEx linearSlideMotor = null;
    public DriveController driveController = null;
    public ArmController armController = null;

    MecanumDrive drive;
    Telemetry telemetry;
    WebcamName webcam;
    public Robot2023(LinearOpMode opMode, MecanumDrive drive, boolean doDriveController,boolean doArmController, boolean doLauncherController){
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.drive = drive;
        this.webcam = hardwareMap.get(WebcamName.class, "Webcam1");

        if (doDriveController){
            driveController = new DriveController();
        }
        if (doArmController){
            armController = new ArmController();
            clawServo = this.hardwareMap.get(Servo.class, "claw");
            linearSlideMotor = this.hardwareMap.get(DcMotorEx.class, "linearSlide");
        }

    }
    public Robot2023(LinearOpMode opMode, MecanumDrive drive){
        this(opMode, drive, true,false, false);
    }
    public void onOpmodeInit(){
        if (driveController != null){
            this.telemetry.log().add("initting drive...");
            this.telemetry.update();
            driveController.onOpmodeInit(this, this.drive, this.telemetry);
        }
    }
    public void doLoop(Gamepad gamepad1, Gamepad gamepad2){
        if (driveController != null){
            driveController.doLoop(gamepad1, gamepad2);
        }
    }
}
