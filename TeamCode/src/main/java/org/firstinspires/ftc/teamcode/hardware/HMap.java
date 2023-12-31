package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Auto.TfodController;
import org.openftc.easyopencv.OpenCvCamera;


public class HMap
{
    public DcMotor  LFMotor = null;
    public DcMotor  RFMotor = null;
    public DcMotor  LBMotor = null;
    public DcMotor  RBMotor = null;
    public DcMotor  armMotor = null;
    public Servo clawServo = null;
    public Servo launcherServo = null;
    public IMU imu = null;
    public WebcamName Webcam1 = null;

    public TfodController tfodController = new TfodController();

    HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HMap(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        //Save reference to hardware map
        hwMap = ahwMap;
        // Define and Initialize Motors
        LFMotor = hwMap.get(DcMotor.class, "leftFront");
        RFMotor = hwMap.get(DcMotor.class, "rightFront");
        LBMotor = hwMap.get(DcMotor.class, "leftBack");
        RBMotor = hwMap.get(DcMotor.class, "rightBack");
        //armMotor = hwMap.get(DcMotor.class, "arm");
        //clawServo = hwMap.get(Servo.class, "claw");
        launcherServo = hwMap.get(Servo.class, "launcher");
        imu = hwMap.get(IMU.class, "imu");
        Webcam1 = hwMap.get(WebcamName.class, "Webcam 1");

        /**IMU.Parameters myIMUparameters;

        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP // depends on install
                        // https://ftc-docs.firstinspires.org/en/latest/programming_resources/imu/imu.html
                        // Link to direction guide.
                )
        );**/

        LFMotor.setDirection(DcMotor.Direction.FORWARD);
        LBMotor.setDirection(DcMotor.Direction.FORWARD);
        RFMotor.setDirection(DcMotor.Direction.REVERSE);
        RBMotor.setDirection(DcMotor.Direction.FORWARD);


        // Set all motors to zero power
        LFMotor.setPower(0);
        RFMotor.setPower(0);
        LBMotor.setPower(0);
        RBMotor.setPower(0);
        //armMotor.setPower(0);

        LFMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RFMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LBMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RBMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to run with encoders.
        LFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set zero power behavior
        LFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}