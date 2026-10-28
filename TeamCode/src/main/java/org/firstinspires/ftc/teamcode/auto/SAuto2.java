package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.ivy.groups.Groups;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystem.Servo1;
import org.firstinspires.ftc.teamcode.subsystem.Servo2;

@Autonomous
public class SAuto2 extends LinearOpMode {
    Follower follower;
    SAutoPath1 path;
    boolean scheduled = false;
     Servo1 servo1;
     Servo2 servo2;
     RobotHardware robot= RobotHardware.getInstance();
    public Command autoRoutine(Follower follower) {
        return sequential(
                Groups.parallel(
                        instant(()-> servo1.updateState(Servo1.servoState.ZERO_POINT_FIVE)),
                        instant(()-> servo2.updateState(Servo2.servoState.ZERO_POINT_FIVE))
                ),
                follow(follower, path.path1()),
                follow(follower, path.path2()),
                Groups.parallel(
                        instant(()-> servo1.updateState(Servo1.servoState.ONE)),
                        instant(()-> servo2.updateState(Servo2.servoState.ONE))
                ),
                follow(follower, path.path3()),
                Groups.parallel(
                        instant(()-> servo1.updateState(Servo1.servoState.ZERO)),
                        instant(()-> servo2.updateState(Servo2.servoState.ZERO))
                ),
                follow(follower, path.path4()),
                Groups.parallel(
                        instant(()-> servo1.updateState(Servo1.servoState.ZERO_POINT_FIVE)),
                        instant(()-> servo2.updateState(Servo2.servoState.ZERO_POINT_FIVE))
                ),
                follow(follower, path.path5()),
                follow(follower, path.path6()),
                follow(follower, path.path7())
        );
    }
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        path = new SAutoPath1();
        follower = Constants.create(hardwareMap);
        follower.setPose(path.startPose);
        servo1 = new Servo1(robot);
        servo2 = new Servo2(robot);

        while (opModeInInit()) {
            Scheduler.reset();
        }
        waitForStart();

        while (opModeIsActive()) {
            follower.update();
            if (!scheduled) {
                schedule(autoRoutine(follower));
            }
            scheduled = true;

            Scheduler.execute();

            telemetry.addData("X", follower.pose().x());
            telemetry.addData("Y", follower.pose().y());
            telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
            telemetry.addData("Follower Mode", follower.mode());
            telemetry.update();
        }
    }
}
