package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@TeleOp(name = "TurnPid", group = "TeleOp")
public class TurnPid extends LinearOpMode {
    HMap robot = new HMap(); //hardware map object
    ElapsedTime runtime = new ElapsedTime();
    FtcDashboard dashboard = FtcDashboard.getInstance();
    double kp = 1;
    double ki = 0;
    double kd = 0;

    double errorSum = 0;
    double lastError =0;
    double lastTime = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        double m = 1;
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double ry = gamepad1.right_stick_y;

            YawPitchRollAngles robotOrientation = robot.imu.getRobotYawPitchRollAngles();
            double botHeading = -robotOrientation.getYaw(AngleUnit.RADIANS);
            telemetry.addData("yaw", robotOrientation.getYaw(AngleUnit.DEGREES));
            telemetry.update();

            if (gamepad1.a) {
                //reset yaw
                robot.imu.resetYaw();
            }

            double targetHeading = Math.atan2(rx, ry);


            double diff = targetHeading - robotOrientation.getYaw(AngleUnit.DEGREES);

            if (diff > 180) {
                diff -= 360;
            }else if (diff < -180){
                diff += 360;
            }

            // #TODO: add Telem vals to FTC dahsboard.
            TelemetryPacket packet = new TelemetryPacket();
            packet.put("Target", Double.toString(targetHeading));
            packet.put("Error", Double.toString((diff)));
            packet.put("Current Value", robotOrientation.getYaw(AngleUnit.DEGREES));

            dashboard.sendTelemetryPacket(packet);



            double currentTime = runtime.milliseconds();
            double error = Math.abs(diff);
            double errorChange = error-lastError;
            errorSum += error * (lastTime - currentTime); // adding to integral sum to continue to aproxamte the integral
            double angleChange = kp*error + ki*errorSum + kd*errorChange;
            lastError = error;
            lastTime = currentTime;


            int mulR = -1;
            int mulL = 1;
            if (diff <0){
                mulL = -1;
                mulR = 1;

            }

            double frontLeftPower =  mulL * angleChange;
            double backLeftPower = mulL * angleChange;
            double frontRightPower = mulR * angleChange;
            double backRightPower = mulR * angleChange;

//            robot.LFMotor.setPower(frontLeftPower);
//            robot.LBMotor.setPower(backLeftPower);
//            robot.RFMotor.setPower(frontRightPower);
//            robot.RBMotor.setPower(backRightPower);
        }
    }
}

