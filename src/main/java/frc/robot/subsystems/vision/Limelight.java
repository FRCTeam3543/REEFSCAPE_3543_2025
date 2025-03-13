// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends SubsystemBase {
    /** Creates a new Limelight. */

    final double CAM_ELEVATION = 0;
    final double CAM_BOT_SHIFT = 0;
    final double TAG_ELEVATION = 0;

    double x = 0;
    double y = 0;
    double v = 0;
    double id = 0;
    double a = 0;
    double surfaceToSurface = 0;
    double surfaceToAir = 0;
    double[] tPose = new double[6];

    public Limelight() {
    }

    public void getX() {
        x = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tx")
                .getDouble(0.0);

    }

    public double giveX() {
        getX();
        return x;
    }

    public void getY() {
        y = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("ty")
                .getDouble(0.0);

    }

    public void getA() {
        a = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("ta")
                .getDouble(0.0);

    }

    public void getV() {
        v = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tv")
                .getDouble(0);

    }

    public void getID() {
        id = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tid")
                .getDouble(0);

    }

    public void getTPose() {
        tPose = NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("targetpose_robotspace")
                .getDoubleArray(new double[6]);

    }

    public void surfaceToSurface() {

        surfaceToSurface = ((TAG_ELEVATION - CAM_ELEVATION) / Math.tan(y)) + CAM_BOT_SHIFT;

    }

    public void surfaceToAir() {

        surfaceToAir = (TAG_ELEVATION - CAM_ELEVATION) / Math.sin(y);

    }

    @Override
    public void periodic() {

        getX();
        getY();
        getA();
        getV();
        getID();
        surfaceToSurface();
        surfaceToAir();

        SmartDashboard.putNumber("Target Horiz", x);
        SmartDashboard.putNumber("Target Vert", y);
        SmartDashboard.putNumber("Target Area", a);
        SmartDashboard.putNumber("Can See?", v);
        SmartDashboard.putNumber("Target ID", id);
        SmartDashboard.putNumber("Ground Distance", surfaceToSurface);
        SmartDashboard.putNumber("Lens To Tag Distance", surfaceToAir);
        SmartDashboard.putNumberArray("Target Pose", tPose);

        int iid = (int) id;

        switch (iid) {
            case 1:
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);

                break;
            case 2:
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(2);

                break;
            case 3:
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

                break;
            default:
                break;
        }
    }
}
