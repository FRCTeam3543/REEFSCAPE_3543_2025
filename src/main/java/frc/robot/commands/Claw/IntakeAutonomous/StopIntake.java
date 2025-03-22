package frc.robot.commands.Claw.IntakeAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;

public class StopIntake extends Command{
    
     private final IntakeSubsystem intakeSubsystem;
    
    public StopIntake(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true; // Since it's a single action command
    }
}
