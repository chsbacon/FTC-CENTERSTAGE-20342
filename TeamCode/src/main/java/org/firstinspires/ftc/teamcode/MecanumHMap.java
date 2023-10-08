package org.firstinspires.ftc.teamcode;

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
        // Define and Initialize Motors
        LTMotor    = hwMap.get(DcMotor.class, "leftFront");
        RTMotor    = hwMap.get(DcMotor.class, "rightFront");
        LBMotor    = hwMap.get(DcMotor.class, "leftBack");
        RBMotor    = hwMap.get(DcMotor.class, "rightBack");

        RBMotor.setDirection(DcMotor.Direction.REVERSE);
        LTMotor.setDirection(DcMotor.Direction.REVERSE);
        LBMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        LTMotor.setPower(0);
        RTMotor.setPower(0);
        LBMotor.setPower(0);
        RBMotor.setPower(0);

        //stop and reset?
        // Set motors to run with encoders.
        LTMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RTMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set zero power behavior
        LTMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RTMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}