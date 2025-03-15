// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Claw.IntakeCommand;
import frc.robot.commands.Claw.RotationCommand;
import frc.robot.commands.Claw.ClawAutonomous.ReverseIntakeCommand;
import frc.robot.commands.Claw.ClawAutonomous.RunIntakeCommand;
import frc.robot.commands.Claw.ClawAutonomous.StopIntakeCommand;
import frc.robot.commands.Climber.ClimberCommand;
import frc.robot.commands.Elevator.ElevatorCommand;
import frc.robot.commands.Elevator.ElevatorAutonomous.MoveElevatorToL1;
import frc.robot.commands.Elevator.ElevatorAutonomous.MoveElevatorToL4;
import frc.robot.commands.swervedrive.drivebase.EuroVision;
import frc.robot.subsystems.Climber.ClimberSubsystem;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.Lights.Lights;
import frc.robot.subsystems.Claw.IntakeSubsystem;
import frc.robot.subsystems.Claw.RotationSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.vision.Limelight;

import java.io.File;
import swervelib.SwerveInputStream;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {

        private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                        "swerve/neo"));

        private final RotationSubsystem rotationSubsystem = new RotationSubsystem();
        private final Lights lights = new Lights();
        private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem(lights);
        private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
        private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
        private final Limelight m_limelight = new Limelight();

        private final SendableChooser<String> autoChooser = new SendableChooser<>();

        SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> Constants.OperatorConstants.driverXbox.getLeftY() * -1,
                        () -> Constants.OperatorConstants.driverXbox.getLeftX() * -1)
                        .withControllerRotationAxis(Constants.OperatorConstants.driverXbox::getRightX)
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);

        SwerveInputStream driveDirectAngle = driveAngularVelocity.copy()
                        .withControllerHeadingAxis(Constants.OperatorConstants.driverXbox::getRightX,
                                        Constants.OperatorConstants.driverXbox::getRightY)
                        .headingWhile(true);

        SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true)
                        .allianceRelativeControl(false);

        SwerveInputStream driveAngularVelocityKeyboard = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> -Constants.OperatorConstants.driverXbox.getLeftY() * 1,
                        () -> -Constants.OperatorConstants.driverXbox.getLeftX() * 1)
                        .withControllerRotationAxis(() -> Constants.OperatorConstants.driverXbox.getRawAxis(
                                        2))
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);

        SwerveInputStream driveDirectAngleKeyboard = driveAngularVelocityKeyboard.copy()
                        .withControllerHeadingAxis(() -> Math.sin(
                                        Constants.OperatorConstants.driverXbox.getRawAxis(
                                                        2) *
                                                        Math.PI)
                                        *
                                        (Math.PI *
                                                        2),
                                        () -> Math.cos(
                                                        Constants.OperatorConstants.driverXbox.getRawAxis(
                                                                        2) *
                                                                        Math.PI)
                                                        *
                                                        (Math.PI *
                                                                        2))
                        .headingWhile(true);

        public RobotContainer() {

                configureBindings();

                DriverStation.silenceJoystickConnectionWarning(true);

                NamedCommands.registerCommand("test", Commands.print("I EXIST"));
                NamedCommands.registerCommand("MoveElevatorToL4", new MoveElevatorToL4(elevatorSubsystem));
                NamedCommands.registerCommand("OuttakeCommand", new ReverseIntakeCommand(intakeSubsystem));
                NamedCommands.registerCommand("MoveElevatorToL1", new MoveElevatorToL1(elevatorSubsystem));
                NamedCommands.registerCommand("StopIntakeCommand", new StopIntakeCommand(intakeSubsystem));
                NamedCommands.registerCommand("IntakeCommand", new RunIntakeCommand(intakeSubsystem));


                autoChooser.setDefaultOption("DRIVE STRAIGHT", "Basic Drive Auto");
                autoChooser.setDefaultOption("DOUBLE L4 LEFT", "DOUBLE L4 AUTO LEFT");
                SmartDashboard.putData("Auto Mode", autoChooser);

                rotationSubsystem.setDefaultCommand(new RotationCommand(rotationSubsystem));
                intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem));
                elevatorSubsystem.setDefaultCommand(new ElevatorCommand(elevatorSubsystem));
                climberSubsystem.setDefaultCommand(new ClimberCommand(climberSubsystem));

                m_limelight.setDefaultCommand(new RunCommand(
                                () -> m_limelight.periodic(), m_limelight));
        }

        private void configureBindings() {
                Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
                Command driveFieldOrientedAnglularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);
                Command driveRobotOrientedAngularVelocity = drivebase.driveFieldOriented(driveRobotOriented);
                Command driveSetpointGen = drivebase.driveWithSetpointGeneratorFieldRelative(
                                driveDirectAngle);
                Command driveFieldOrientedDirectAngleKeyboard = drivebase.driveFieldOriented(driveDirectAngleKeyboard);
                Command driveFieldOrientedAnglularVelocityKeyboard = drivebase
                                .driveFieldOriented(driveAngularVelocityKeyboard);
                Command driveSetpointGenKeyboard = drivebase.driveWithSetpointGeneratorFieldRelative(
                                driveDirectAngleKeyboard);
                new Trigger(() -> Constants.OperatorConstants.driverXbox.button(5).getAsBoolean() ||
                                Constants.OperatorConstants.driverXbox.button(6).getAsBoolean() ||
                                Constants.OperatorConstants.driverXbox.button(7).getAsBoolean())
                                .onTrue(new EuroVision(drivebase, null, null, null, null, m_limelight));

                if (RobotBase.isSimulation()) {
                        drivebase.setDefaultCommand(driveFieldOrientedDirectAngleKeyboard);
                } else {
                        drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);
                }

        }

        public Command getAutonomousCommand() {
                String selectedAuto = autoChooser.getSelected();
                return drivebase.getAutonomousCommand(selectedAuto);
        }

        public void setMotorBrake(boolean brake) {
                drivebase.setMotorBrake(brake);
        }
}