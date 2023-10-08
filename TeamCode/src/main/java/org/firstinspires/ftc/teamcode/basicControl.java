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
        waitForB_Button();
        telemetry.addData("B", runtime.seconds());
        telemetry.update();
        runForward();
        telemetry.addData("moved forward", runtime.seconds());
        telemetry.update();
        //turnLeft();
        //turnRight();
        //turnLeftAndMove();
        //turnRightAndMove();
        //turn180();
        //moveBackwards();
        //*Main code, runs after start button pressed
    }


    //* Waits for "B" button to be pressed
    //Hint: "game pad1.b" returns true if "B" pressed, false if not
    public void waitForB_Button(){
        while(true){
            if (gamepad1.b){
                break;
            }
        }
    }

    //* Robot goes forward 5 seconds
    public void runForward(){
        while(runtime.seconds() <= 5 && opModeIsActive()){
            robot.LTMotor.setPower(.1);
            robot.RTMotor.setPower(.1);
            robot.LBMotor.setPower(.1);
            robot.RBMotor.setPower(-.1);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);


    }
    public void turnLeft(){
        resetRuntime();
        while(runtime.seconds() <= 10 && opModeIsActive()){
            robot.LTMotor.setPower(.1);
            robot.RTMotor.setPower(.1);
            robot.LBMotor.setPower(.1);
            robot.RBMotor.setPower(.1);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();

        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);

    }
    public void turnRight(){
        resetRuntime();
        while(runtime.seconds() <= 5 && opModeIsActive()){
            robot.LTMotor.setPower(-.1);
            robot.RTMotor.setPower(.1);
            robot.LBMotor.setPower(-.1);
            robot.RBMotor.setPower(.1);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();

        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);

    }
    public void turnLeftAndMove(){
        turnLeft();
        runForward();

    }
    public void turnRightAndMove(){
        turnRight();
        runForward();

    }
    public void turn180(){
        resetRuntime();
        while(runtime.seconds() <= 10 && opModeIsActive()){
            robot.LTMotor.setPower(.1);
            robot.RTMotor.setPower(-.1);
            robot.LBMotor.setPower(.1);
            robot.RBMotor.setPower(-.1);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();

        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);

    }
    public void moveBackwards() {
        resetRuntime();
        while (runtime.seconds() <= 5 && opModeIsActive()) {
            robot.LTMotor.setPower(-0.1);
            robot.RTMotor.setPower(-0.1);
            robot.LBMotor.setPower(-0.1);
            robot.RBMotor.setPower(-0.1);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }


}



