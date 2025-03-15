package frc.robot.commands.Claw.ClawAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;

public class ReverseIntakeCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;

    public ReverseIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.runIntake(-1);
    }

    @Override
    public boolean isFinished() {
        return true; // Runs once
    }
}