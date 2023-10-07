package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This file is a basic auto opmode template
 **/

@Autonomous(name="YourAuto", group="Autonomous")
//@Disabled

public class YourAuto extends LinearOpMode { //* Change name to your liking

    //* Variable Declaration
    MecanumHMap robot = new MecanumHMap(); //hardware map object

    //Main method, runs after init button pressed on DS
    @Override
    public void runOpMode() {

        //*Initialization code (hardware)
        waitForStart();

        //*Main code, runs after start button pressed
    }


    //* Waits for "B" button to be pressed
    //Hint: "gamepad1.b" returns true if "B" pressed, false if not
    public void waitForB_Button(){

    }

    //* Robot goes forward _ seconds
    public void runForward(){

    }
}



