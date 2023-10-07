package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Hardware map for robot with encoders
 **/

public class MecanumHMap
{
    /* Public OpMode members. */
    public DcMotor  LTMotor    = null;
    public DcMotor  RTMotor     = null;
    public DcMotor  LBMotor    = null;
    public DcMotor  RBMotor      = null;
    public DcMotor  armMotor      = null;

    public RevBlinkinLedDriver blinkinLedDriver = null;
    public RevBlinkinLedDriver.BlinkinPattern pattern;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public MecanumHMap(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        //Reverse right motor
        // Save reference to Hardware map
        hwMap = ahwMap;
        blinkinLedDriver = hwMap.get(RevBlinkinLedDriver.class, "blinkin");
        // Define and Initialize Motors
        LTMotor    = hwMap.get(DcMotor.class, "LH");
        RTMotor    = hwMap.get(DcMotor.class, "RH");
        LBMotor    = hwMap.get(DcMotor.class, "FV");
        RBMotor    = hwMap.get(DcMotor.class, "BV");
        armMotor   = hwMap.get(DcMotor.class, "arm");

        LTMotor.setDirection(DcMotor.Direction.REVERSE);
        LBMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        LTMotor.setPower(0);
        RTMotor.setPower(0);
        LBMotor.setPower(0);
        RBMotor.setPower(0);
        armMotor.setPower(0);

        //stop and reset?
        // Set motors to run with encoders.
        LTMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RTMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set zero power behavior
        LTMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RTMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}