package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.ftccommon.configuration.RobotConfigResFilter;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.Modules.DriveController;
import org.firstinspires.ftc.teamcode.Modules.Robot2023;

@TeleOp
public class BasicTeleOp extends LinearOpMode {
    private DriveController driveController;
    public void runOpMode(){
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        Robot2023 robot = new Robot2023(this, drive, true,false, false);
        robot.onOpmodeInit();
        waitForStart();
        while (opModeIsActive()){
            robot.doLoop(gamepad1, gamepad2);
            idle();
        }
    }
}
