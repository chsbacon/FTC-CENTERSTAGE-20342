package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="armTest", group="TeleOp")
//@Disabled
public class armTest extends LinearOpMode {
    HMap robot = new HMap();
    ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.x) {
                telemetry.addData("Forward", runtime.seconds());
                telemetry.update();
                while (gamepad1.x) {
                    robot.arm.setPower(.5);
                }
                robot.arm.setPower(0);
            }
            if (gamepad1.y) {
                telemetry.addData("Backwards", runtime.seconds());
                telemetry.update();
                while (gamepad1.y) {
                    robot.arm.setPower(-.5);
                }
                robot.arm.setPower(0);
            }
        }
    }
}
