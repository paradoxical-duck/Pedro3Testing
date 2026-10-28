package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import static java.util.concurrent.locks.LockSupport.park;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class SAutoPath1 {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    public final Pose startPose = poseFactory.of(63.0235, 9.4674, 90);
    private final Pose point1 = poseFactory.of(51.4989, 128.2668, 140);
    private final Pose point1Control1 = poseFactory.of(85.2139, 100.1134, 0);
    private final Pose point2Start = poseFactory.of(51.4989, 128.2668, 140);
    private final Pose point2 = poseFactory.of(12.415, 51.9401, 220);
    private final Pose point2Control1 = poseFactory.of(19.1511, 96.2224, 0);
    private final Pose point3Start = poseFactory.of(12.415, 51.9401, 220);
    private final Pose point3 = poseFactory.of(63.0235, 9.4674, 180);
    private final Pose point4 = poseFactory.of(9.8481, 7.3487, -177.7183);
    private final Pose point5 = poseFactory.of(9.792, 7.3487, 180);
    private final Pose point6 = poseFactory.of(63.0235, 9.4861, -177.7006);
    private final Pose point7 = poseFactory.of(13.3988, 92.707, 97.6053);
    private final Pose point7Control1 = poseFactory.of(18.7138, 51.4447, 0);

    public Path path1() {
        return Paths.curve(startPose, point1Control1, point1).linear(startPose, point1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point3Start, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.line(point3, point4).tangent();
    }

    public Path path5() {
        return Paths.line(point4, point5).constant(point5);
    }

    public Path path6() {
        return Paths.line(point5, point6).reverseTangent();
    }

    public Path path7() {
        return Paths.curve(point6, point7Control1, point7).tangent();
    }
    public Command autoRoutine(Follower follower) {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4()),
                follow(follower, path5()),
                follow(follower, path6()),
                follow(follower, path7())
        );
    }
}