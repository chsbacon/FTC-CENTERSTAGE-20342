package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmController {
    private enum ArmState {
        idle,
        ReleasePositon,
    }

    private enum ClawState {
        Open,
        Closed,
    }

    private Robot2023 robot;

    private Telemetry telemetry;
    private final double CLAW_OPEN = 180; // DO NOT RUN UNTIL CALIBRATED
    private final double CLAW_CLOSED = 0; // DO NOT RUN UNTIL CALIBRATED
    private final int linearSlideTicksUntilOpen = 800; // DO NOT RUN UNTIL CALIBRATED


    private ArmState arm_State = ArmState.idle;
    private ClawState claw_State = ClawState.Closed; //  enums are sloppy


    public void onOpmodeInit(Robot2023 robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;
        for(DcMotorEx motor: new DcMotorEx[]{robot.linearSlideMotor}){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(0);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION); //  means that i can set the motor to go to a tick number/position
            motor.setPower(1);
        }
    }


    public void doLoop(Gamepad gampad1,Gamepad gampad2){
        if (gampad2.right_trigger > 0.5){
            openClaw();
        }
        if (gampad2.left_trigger > 0.5){
            closeClaw();
        }
        if (gampad1.dpad_up ){
            linearSlideUp();
        }
        if (gampad1.dpad_down){
            linearSlideDown();
        }
    }

    public void closeClaw(){
        robot.clawServo.setPosition(CLAW_CLOSED);
        claw_State = ClawState.Closed;
    }

    public void openClaw(){ // ALSO CAN USE THESE FOR AUTO
        robot.clawServo.setPosition(CLAW_OPEN);
        claw_State = ClawState.Open;
    }

    public void linearSlideUp(){
        robot.linearSlideMotor.setTargetPosition(linearSlideTicksUntilOpen);
        arm_State = ArmState.ReleasePositon;
    }

    public void linearSlideDown(){
        robot.linearSlideMotor.setTargetPosition(0);
        arm_State = ArmState.ReleasePositon;
    }


    public void releasePeiceAtTop() { // combines the action of opening the claw and moving the linear slide up possible use in auto
        if (claw_State == ClawState.Closed && arm_State == ArmState.idle) {
            robot.linearSlideMotor.setTargetPosition(linearSlideTicksUntilOpen);
            arm_State = ArmState.ReleasePositon;
        } else {
            robot.clawServo.setPosition(CLAW_OPEN);
            claw_State = ClawState.Open;
            robot.linearSlideMotor.setTargetPosition(linearSlideTicksUntilOpen);
            arm_State = arm_State.ReleasePositon;
        }

    }
}
