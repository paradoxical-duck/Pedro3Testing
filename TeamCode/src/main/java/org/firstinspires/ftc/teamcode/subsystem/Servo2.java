package org.firstinspires.ftc.teamcode.subsystem;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

public class Servo2 {
    RobotHardware robot;
    public Servo2 (RobotHardware robot) {
        this.robot = robot;
    }
    public enum servoState {
        ZERO,
        ZERO_POINT_FIVE,
        ONE
    }
    servoState state = servoState.ZERO;
    public void updateState(servoState newState) {
        double target = 0;
        switch (newState) {
            case ZERO:
                target = 0;
                break;
            case ZERO_POINT_FIVE:
                target = 0.5;
                break;
            case ONE:
                target = 1;
                break;

        }
        robot.servo2.setPosition(target);
        state = newState;
    }
}
