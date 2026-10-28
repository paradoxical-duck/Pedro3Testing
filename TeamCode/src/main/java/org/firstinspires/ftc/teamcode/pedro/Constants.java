package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("left front ");
        c.frontRightName.set("right front ");
        c.backLeftName.set("left back");
        c.backRightName.set("right back ");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
//        c.powerThreshold
//        c.manualBrakeMode
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-0.9315551547553597);
        c.yPodOffset.set(1.8616443543922245);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.2344396287566468);
                Controller secondaryTranslationalForward = Controller.proportional(0.08661917848332859);
                Controller primaryTranslationalLateral = Controller.proportional(0.34502694172613163);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1274782356781286);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.014417402147214698));
                c.brake.set(Controller.proportionalFeedforward(0.012254791825132493));

                c.headingFeedback.set(Controller.proportional(3.123391506062286));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.057202997706985774, 0.010458468524455707));

                c.linearBrakeCoefficients.set(Matrix.diag(0.09669756112384015, 0.060561176735847475));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.00245751585985896, 0.003321324279318165));

                c.maxAchievableForwardVelocity.set(70.73906620750157);
                c.maxAchievableStrafeVelocity.set(55.60972320500548);
                c.naturalForwardDeceleration.set(45.73460382566569);
                c.naturalStrafeDeceleration.set(69.51154942378395);
            }
    );
}