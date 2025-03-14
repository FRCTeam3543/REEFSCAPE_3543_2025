package frc.robot.commands.Claw.ClawAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StopIntakeCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;

    public StopIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true; // Runs once
    }
}
