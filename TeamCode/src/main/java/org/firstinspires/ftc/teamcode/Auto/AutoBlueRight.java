package org.firstinspires.ftc.teamcode.Auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.HMap;

@Autonomous(name = "AutoBlueRight", group = "TeleOp")

public class AutoBlueRight extends LinearOpMode{
    HMap robot = new HMap();
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV = 537.7;
    static final double     WHEEL_DIAMETER_INCHES = 96.0/25.4;
    static final double     COUNTS_PER_INCH = COUNTS_PER_MOTOR_REV / WHEEL_DIAMETER_INCHES * 3.1415;
    static final double DRIVE_SPEED = 0.1;
    static final double TURN_SPEED = 0.1;

    private FieldPositions.SpikeMarkLocation spikeMarkLocation;

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.LFMotor.getCurrentPosition(),
                robot.RFMotor.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        if(robot.tfodController != null){
            double lastX = robot.tfodController.lastX;
            // here's where the thresholds for position detection are
            // with of screen is 640, so have the windows be each third of the screen
            if(lastX < 640/3){
                // left spike mark
                left();
            } else if (lastX < 640*2/3){
                // center spike mark
                center();
            } else {
                // right spike mark
                right();
            }
            telemetry.log().add("LastX is " + robot.tfodController.lastX + ", so spike mark location is " + spikeMarkLocation.toString());
        } else {
            // if the tfod isn't running, assume we're center spike mark
            spikeMarkLocation = FieldPositions.SpikeMarkLocation.Center;
            center();
            telemetry.log().add("Cound not get TFOD controller, assuming center mark");
        }
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed, double leftInches, double rightInches) {
        int newLFTarget;
        int newLBTarget;
        int newRFTarget;
        int newRBTarget;

        // Ensure that the opmode is still active
        if (!opModeIsActive()) return;

        // Determine new target position, and pass to motor controller
        newLFTarget = robot.LFMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newLBTarget = robot.LBMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRFTarget = robot.RFMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        newRBTarget = robot.RBMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        robot.LFMotor.setTargetPosition(newLFTarget);
        robot.LBMotor.setTargetPosition(newLBTarget);
        robot.RFMotor.setTargetPosition(newRFTarget);
        robot.RBMotor.setTargetPosition(newRBTarget);

        // Turn On RUN_TO_POSITION
        robot.LFMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.LBMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RFMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RBMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        robot.LFMotor.setPower(Math.abs(speed));
        robot.LBMotor.setPower(Math.abs(speed));
        robot.RFMotor.setPower(Math.abs(speed));
        robot.RBMotor.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        while (opModeIsActive() &&
                (robot.LFMotor.isBusy() && robot.RFMotor.isBusy())) {
            telemetry.addData("Path1",  "Running to %7d :%7d", newLFTarget,  newRFTarget);
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    robot.LFMotor.getCurrentPosition(),
                    robot.RFMotor.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        robot.LFMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RFMotor.setPower(0);
        robot.RBMotor.setPower(0);

        // Turn off RUN_TO_POSITION
        robot.LFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sleep(250);   // optional pause after each move
    }

    public void left() {
        encoderDrive(DRIVE_SPEED,  25, 25);
        encoderDrive(TURN_SPEED,  -12, 12);
        encoderDrive(DRIVE_SPEED,  -4, -4);
        encoderDrive(TURN_SPEED,  12, -12);
        encoderDrive(DRIVE_SPEED,  -24, -24);
        encoderDrive(TURN_SPEED,  -12, 12);
        encoderDrive(DRIVE_SPEED,  92, 92);
        telemetry.addData("Left:", runtime);
    }
    public void center() {
        encoderDrive(DRIVE_SPEED,  25,  25);
        encoderDrive(DRIVE_SPEED,  -24,  -24);
        encoderDrive(TURN_SPEED,  -12, 12);
        encoderDrive(DRIVE_SPEED,  96, 96);
        telemetry.addData("Center:", runtime);
    }
    public void right() {
        encoderDrive(DRIVE_SPEED,  25, 25);
        encoderDrive(TURN_SPEED,  12, -12);
        encoderDrive(DRIVE_SPEED,  -4, -4);
        encoderDrive(TURN_SPEED,  -12, 12);
        encoderDrive(DRIVE_SPEED,  -24, -24);
        encoderDrive(TURN_SPEED,  -12, 12);
        encoderDrive(DRIVE_SPEED,  92, 92);
        telemetry.addData("Right:", runtime);
    }
}