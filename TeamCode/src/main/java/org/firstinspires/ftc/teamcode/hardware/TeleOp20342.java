package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TeleOp20342", group = "TeleOp")
public class TeleOp20342 extends LinearOpMode {
    HMap robot = new HMap(); //hardware map object

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){
            double speed  = .75;

            robot.LFMotor.setDirection(DcMotor.Direction.REVERSE);
            robot.LBMotor.setDirection(DcMotor.Direction.REVERSE);
            robot.RFMotor.setDirection(DcMotor.Direction.FORWARD);
            robot.RBMotor.setDirection(DcMotor.Direction.REVERSE);

            double m = Math.hypot(gamepad1.right_stick_x, gamepad1.left_stick_y);
            double tAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.right_stick_x) - Math.PI / 4;
            double turn = -gamepad1.left_stick_x * 0.7;

            double LFpwr = (m * Math.cos(tAngle) + turn) * speed;
            double LBpwr = (m * Math.sin(tAngle) + turn) * speed;
            double RFpwr = (m * Math.sin(tAngle) - turn) * speed;
            double RBpwr = (m * Math.cos(tAngle) - turn) * speed;

            double turnScale = Math.max(Math.max(Math.abs(LFpwr), Math.abs(LBpwr)),
                    Math.max(Math.abs(RFpwr), Math.abs(RBpwr)));
            if (Math.abs(turnScale) < 1.0) turnScale = 1.0;

            double launcherPos = .6;
            if (gamepad2.x) {
                launcherPos = 0;
            }
            else {
                launcherPos = .6;
            }
            telemetry.addData("Launcher: ", robot.launcherServo.getPosition());
            telemetry.update();

            /**double armPwr = 0;
            if (gamepad1.right_trigger > 0.75) {
                  armPwr = .5;
            }
            else if (gamepad1.left_trigger > 0.75) {
                armPwr = -.5;
            }
            else {
                armPwr = 0;
            }

             double clawPwr = 0;
             if (gamepad1.y) {
             clawPwr = 1;
             telemetry.addData("Claw: ", clawPwr);
             }
             else {
             clawPwr = 0;
             }**/

            // set the motors
            robot.LFMotor.setPower(LFpwr / turnScale);
            robot.RBMotor.setPower(LBpwr / turnScale);
            robot.RFMotor.setPower(RFpwr / turnScale);
            robot.LBMotor.setPower(RBpwr / turnScale);
            robot.launcherServo.setPosition(launcherPos);
            //robot.armMotor.setPower(armPwr);
            //robot.clawServo.setPosition(clawPwr);
        }
    }

}