package org.firstinspires.ftc.teamcode.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LauncherController {

    private int sevoReleasePosition = 0;

    private enum launcherState {
        idle,
        relased,
    }

    private Robot2023 robot;

    private Telemetry telemetry;

    private launcherState launcher_state = launcherState.idle;

    public void onOpmodeInit(Robot2023 robot, Telemetry telemetry) {
        this.robot = robot;
        this.telemetry = telemetry;
    }

    public void doLoop(Gamepad gampad1, Gamepad gampad2) {
        if (gampad2.left_bumper) {
            releaseLauncher();
        }
    }

    public void releaseLauncher(){
        robot.launcherServo.setPosition(sevoReleasePosition);
        launcher_state = launcherState.relased;
    }


}
