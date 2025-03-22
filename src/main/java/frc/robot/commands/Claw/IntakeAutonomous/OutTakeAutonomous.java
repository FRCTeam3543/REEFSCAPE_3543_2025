package frc.robot.commands.Claw.IntakeAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;

public class OutTakeAutonomous extends Command{

    private final IntakeSubsystem intakeSubsystem;
    
    public OutTakeAutonomous(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.launch();
    }

    @Override
    public boolean isFinished() {
        return true; // Since it's a single action command
    }
}
