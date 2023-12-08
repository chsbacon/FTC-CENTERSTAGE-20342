package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    Servo clawServo;
    public DcMotorEx linearExtenderMotor;
    public DcMotorEx rightForearmMotor;
    public DcMotorEx leftForearmMotor;
    public DcMotorEx intakeMotor;
    public TfodController tfodController = null;
    public AutonomousController autonomousController = null;
    MecanumDrive drive;
    Telemetry telemetry;
    WebcamName webcam;

    public Robot2023(LinearOpMode opMode, MecanumDrive drive, boolean doArmController, boolean doDriveController, boolean doAprilTags, boolean doTfod, boolean doAuto, boolean doIntake){
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.drive = drive;
        // conditional ha

        // controller inits
        if (doTfod){
            tfodController = new TfodController();
        }
        if (doAuto){
            autonomousController = new AutonomousController();
        }
    }
    public Robot2023(LinearOpMode opMode, MecanumDrive drive){
        this(opMode, drive, true, true, false,false, false, true);
    }
    public void onOpmodeInit(){
        //drive.imu.resetYaw();
        if (autonomousController != null){
            autonomousController.onOpmodeInit(this, this.telemetry);
        }
        if (tfodController != null) {
            if (autonomousController != null) {
                tfodController.onOpmodeInit(this, this.telemetry, autonomousController.team);
            } else {
                telemetry.log().add("WARNING: tfod controller is running without an autonomous controller");
                tfodController.onOpmodeInit(this, this.telemetry, FieldPositions.Team.Blue);
            }
        }
    }
    public void doLoop(Gamepad gamepad1, Gamepad gamepad2){
        if (tfodController != null){
            tfodController.doLoop(gamepad1, gamepad2);
        }
        if(autonomousController != null){
            autonomousController.doLoop();
        }
    }

}