package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "TestField", group = "TeleOp")
public class TestField extends LinearOpMode {
    HMap robot = new HMap(); //hardware map object
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        double m = 1;
        robot.init(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            if (gamepad1.right_trigger > 0.75) {
                m = 0.5;
            } else if (gamepad1.left_trigger > 0.75) {
                m = 0.2;
            } else {
                m = 1;
            }

            // Read inverse IMU heading, as the IMU heading is CW positive
            YawPitchRollAngles robotOrientation = robot.imu.getRobotYawPitchRollAngles();
            double botHeading = -robotOrientation.getYaw(AngleUnit.RADIANS);
            telemetry.addData("yaw", robotOrientation.getYaw(AngleUnit.DEGREES));
            telemetry.update();
            if (gamepad1.a) {
                robot.imu.resetYaw();
            }
            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = m * (rotY + rotX + rx) / denominator;
            double backLeftPower = m * (rotY - rotX + rx) / denominator;
            double frontRightPower = m * (rotY - rotX - rx) / denominator;
            double backRightPower = m * (rotY + rotX - rx) / denominator;

            robot.LFMotor.setPower(frontLeftPower);
            robot.LBMotor.setPower(backLeftPower);
            robot.RFMotor.setPower(frontRightPower);
            robot.RBMotor.setPower(backRightPower);
        }
    }
}