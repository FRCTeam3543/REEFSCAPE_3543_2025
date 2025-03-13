package frc.robot.commands.Elevator.ElevatorAutonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;

public class MoveElevatorToL4 extends Command {
    private final ElevatorSubsystem elevatorSubsystem;

    public MoveElevatorToL4(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        elevatorSubsystem.moveToCoralL4();
    }

    @Override
    public boolean isFinished() {
        return true; // Since it's a single action command
    }
}
