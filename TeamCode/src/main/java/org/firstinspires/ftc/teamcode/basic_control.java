package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="YourAuto", group="Autonomous")
//@Disabled

    /* Initialize standard Hardware interfaces */
public class myAuto extends LinearOpMode { //* Change name to your liking

    //* Variable Declaration
    MecanumHMap robot = new MecanumHMap(); //hardware map object
    ElapsedTime runtime = new ElapsedTime();

    //Main method, runs after init button pressed on DS
    @Override
    public void runOpMode() {

        //*Initialization code (hardware)
        waitForStart();


        waitForB_Button();
        runForward();
        turnLeft();
        turnRight();
        turnLeftAndMove();
        turnRightAndMove();
        turn180();
        moveBackwards();
        //*Main code, runs after start button pressed
    }


    //* Waits for "B" button to be pressed
    //Hint: "game pad1.b" returns true if "B" pressed, false if not
    public void waitForB_Button(){
        while(!gamepad1.b){
            sleep(1);
            if (gamepad1.b){
                break;
            }
        }



    }

    //* Robot goes forward 5 seconds
    public void runForward(){
        resetRuntime();
        while(runtime.seconds() <= 5 && opModeIsActive()){
            robot.LTMotor.setPower(.5);
            robot.RTMotor.setPower(.5);
            robot.LBMotor.setPower(.5);
            robot.RBMotor.setPower(.5);
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
        while(runtime.seconds() <= 5 && opModeIsActive()){
            robot.LTMotor.setPower(.5);
            robot.RTMotor.setPower(-.5);
            robot.LBMotor.setPower(.5);
            robot.RBMotor.setPower(-.5);
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
            robot.LTMotor.setPower(-.5);
            robot.RTMotor.setPower(.5);
            robot.LBMotor.setPower(-.5);
            robot.RBMotor.setPower(.5);
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
            robot.LTMotor.setPower(.5);
            robot.RTMotor.setPower(-.5);
            robot.LBMotor.setPower(.5);
            robot.RBMotor.setPower(-.5);
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
            robot.LTMotor.setPower(-0.5);
            robot.RTMotor.setPower(-0.5);
            robot.LBMotor.setPower(-0.5);
            robot.RBMotor.setPower(-0.5);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();
        }
        robot.LTMotor.setPower(0);
        robot.RTMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RBMotor.setPower(0);
    }


}



