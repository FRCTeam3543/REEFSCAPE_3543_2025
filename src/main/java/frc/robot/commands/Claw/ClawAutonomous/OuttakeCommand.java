package frc.robot.commands.Claw.ClawAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OuttakeCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;

    public OuttakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem); // Ensures scheduler handles conflicts
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("Outtake Status", "STARTING OUTTAKE");
    }

    @Override
    public void execute() {
        intakeSubsystem.launch(); // Runs outtake function
        SmartDashboard.putString("Outtake Status", "OUTTAKING CORAL");
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop(); // Stops intake system
        SmartDashboard.putString("Outtake Status", "OUTTAKE STOPPED");
    }
}
