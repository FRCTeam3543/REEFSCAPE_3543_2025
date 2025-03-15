package frc.robot.commands.Claw.ClawAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;

// Command to run the intake at a given speed
public class RunIntakeCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;

    public RunIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.runIntake(0.6);
    }

    @Override
    public boolean isFinished() {
        return true; // Runs once
    }
}