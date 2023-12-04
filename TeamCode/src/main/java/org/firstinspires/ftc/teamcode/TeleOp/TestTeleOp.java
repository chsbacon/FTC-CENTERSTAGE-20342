package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.HashMap;
import java.util.Map;

@TeleOp(name = "TestTeleOp", group = "TeleOp")
public class TestTeleOp extends LinearOpMode {
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

            // set the motors
            robot.LFMotor.setPower(LFpwr / turnScale);
            robot.RBMotor.setPower(LBpwr / turnScale);
            robot.RFMotor.setPower(RFpwr / turnScale);
            robot.LBMotor.setPower(RBpwr / turnScale);

        }
    }

}
