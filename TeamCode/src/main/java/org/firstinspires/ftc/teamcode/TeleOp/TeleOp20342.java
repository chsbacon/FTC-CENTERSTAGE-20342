package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "TeleOp20342", group = "TeleOp")
public class TeleOp20342 extends LinearOpMode {
    HMap robot = new HMap(); //hardware map object
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()){
            double m = Math.hypot(gamepad1.right_stick_x, gamepad1.left_stick_y);
            double tAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.right_stick_x) - Math.PI / 4;
            double turn = -gamepad1.left_stick_x * 0.7;

            double LFpwr = m * Math.cos(tAngle) + turn;
            double LBpwr = m * Math.sin(tAngle) + turn;
            double RFpwr = m * Math.sin(tAngle) - turn;
            double RBpwr = m * Math.cos(tAngle) - turn;

            double turnScale = Math.max(Math.max(Math.abs(LFpwr), Math.abs(LBpwr)),
                    Math.max(Math.abs(RFpwr), Math.abs(RBpwr)));
            if (Math.abs(turnScale) < 1.0) turnScale = 1.0;

            double armPwr = 0;
            if (gamepad1.right_trigger > 0.75) {
                  armPwr = .5;
            }
            else if (gamepad1.left_trigger > 0.75) {
                armPwr = -.5;
            }
            else {
                armPwr = 0;
            }

            double launcherPwr = 1;
            if (gamepad1.x) {
                launcherPwr = 1;
                telemetry.addData("Launcher: ", robot.launcherServo.getPosition());
            }
            else {
                launcherPwr = 0;
                telemetry.addData("Launcher: ", robot.launcherServo.getPosition());
            }

            double clawPwr = 0;
            if (gamepad1.y) {
                clawPwr = 1;
                telemetry.addData("Claw: ", clawPwr);
            }
            else {
                clawPwr = 0;
            }
            telemetry.update();

            // set the motors
            robot.LFMotor.setPower(LFpwr / turnScale);
            robot.RBMotor.setPower(LBpwr / turnScale);
            robot.RFMotor.setPower(RFpwr / turnScale);
            robot.LBMotor.setPower(RBpwr / turnScale);
            robot.armMotor.setPower(armPwr);
            robot.launcherServo.setPosition(launcherPwr);
            robot.clawServo.setPosition(clawPwr);
        }
    }

}
