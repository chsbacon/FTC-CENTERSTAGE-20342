package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="YourAuto", group="Autonomous")
//@Disabled
    /* Initialize standard Hardware interfaces */
public class basicControl extends LinearOpMode { //* Change name to your liking
    //* Variable Declaration
    MecanumHMap robot = new MecanumHMap(); //hardware map object
    ElapsedTime runtime = new ElapsedTime();
    //Main method, runs after init button pressed on DS
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        //*Initialization code (hardware)
        waitForStart();
        telemetry.addData("start", runtime.seconds());
        telemetry.update();
        while(opModeIsActive()) {
            if (gamepad1.dpad_up) {
                runForward();
                telemetry.addData("moved forward", runtime.seconds());
                telemetry.update();
            }
            if (gamepad1.dpad_left) {
                turnLeft();
                telemetry.addData("turned Left", runtime.seconds());
                telemetry.update();
            }
            if (gamepad1.dpad_right) {
                turnRight();
                telemetry.addData("turned right", runtime.seconds());
                telemetry.update();
            }
            if (gamepad1.dpad_down) {
                moveBackwards();
                telemetry.addData("turned backwards", runtime.seconds());
                telemetry.update();

            }
        }
        //waitForB_Button();
        //telemetry.addData("B", runtime.seconds());
        //telemetry.update();

        //turnRight();
        //moveBackwards();
        //*Main code, runs after start button pressed
    }
    //* Robot goes forward 5 seconds
    public void runForward(){
        while(gamepad1.dpad_up){
            robot.LTMotor.setPower(.1);
            robot.RTMotor.setPower(.1);
            robot.LBMotor.setPower(.1);
            robot.RBMotor.setPower(.1);
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }
    public void turnLeft(){
        while(gamepad1.dpad_left){
            robot.LTMotor.setPower(-.1);
            robot.RTMotor.setPower(.1);
            robot.LBMotor.setPower(-.1);
            robot.RBMotor.setPower(.1);
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }
    public void turnRight(){
        while(gamepad1.dpad_right){
            robot.LTMotor.setPower(.1);
            robot.RTMotor.setPower(-.1);
            robot.LBMotor.setPower(.1);
            robot.RBMotor.setPower(-.1);
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }
    public void moveBackwards() {
        while (gamepad1.dpad_down) {
            robot.LTMotor.setPower(-.1);
            robot.RTMotor.setPower(-.1);
            robot.LBMotor.setPower(-.1);
            robot.RBMotor.setPower(-.1);
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }
}