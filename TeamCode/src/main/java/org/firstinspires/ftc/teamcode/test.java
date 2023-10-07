package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This file is a basic auto opmode template
 **/

@Autonomous(name="YourAuto", group="Autonomous")
//@Disabled

public class myAuto extends LinearOpMode { //* Change name to your liking

    //* Variable Declaration
    MecanumHMap robot = new MecanumHMap(); //hardware map object

    //Main method, runs after init button pressed on DS
    @Override
    public void runOpMode() {

        //*Initialization code (hardware)
        waitForStart();


        waitForB_Button();
        runForward();
        turnLeft();
        runForward();
        turnRight();
        turnRight();
        turnLeftAndMove();
        for (int i = 0; i<5; i++){
            turnRightAndMove();
        }
        turn180();
        moveBackWards();

        //*Main code, runs after start button pressed
    }


    //* Waits for "B" button to be pressed
    //Hint: "gamepad1.b" returns true if "B" pressed, false if not
    public void waitForB_Button(){
        while(gamepad1.b = false){
            wait();
            if (gamepad1.b = true){
                break;
            }
        }



    }

    //* Robot goes forward 5 seconds
    public void runForward(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Rmotor.setpower(.5);
            robot.Lmotor.setpower(.5);
            telemetry.addData("forward", runtime.seconds());
            telemetry.update();
        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);


    }
    public void turnLeft(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Rmotor.setpower(.5);
            robot.Lrobot.setpower(-.5);

        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);

    }
    public void turnRight(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Lmotor.setpower(.5);
            robot.Rmotor.setpower(-.5);

        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);

    }
    public void turnLeftAndMove(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Rmotor.setpower(.5);
            robot.Lmotor.setpower(-.5);

        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);
        runForward();

    }
    public void turnRightAndMove(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Rmotor.setpower(-.5);
            robot.Lmotor.setpower(.5);

        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);
        runForward();

    }
    public void turn180(){
        resetRuntime();
        while(runtime.seconds <= 10 && opModeIsActive()){
            robot.Rmotor.setpower(-.5);
            robot.Lmotor.setpower(.5);

        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);

    }
    public void moveBackwards(){
        resetRuntime();
        while(runtime.seconds <= 5 && opModeIsActive()){
            robot.Rmotor.setpower(-.5);
            robot.Lmotor.setpower(-.5);
        }
        robot.Rmotor.setpower(0);
        robot.Lmotor.setpower(0);
    }


}



