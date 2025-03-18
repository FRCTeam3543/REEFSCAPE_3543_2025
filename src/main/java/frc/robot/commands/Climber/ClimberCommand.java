package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber.ClimberSubsystem;
import frc.robot.Constants;

public class ClimberCommand extends Command {

    private final ClimberSubsystem ClimberSubsystem;

    public ClimberCommand(ClimberSubsystem climberSubsystem) {
        this.ClimberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem); // Important so the scheduler handles conflicts
    }

    public static final int ClimberUp = 1;
    public static final int ClimberDown = 2;
    public static final int NONE = 0;

    int getTargetPosition() {
        if (Constants.OperatorConstants.driverXbox.button(3).getAsBoolean()) {
            return ClimberDown;
        }
        return NONE;
    }

    @Override
    public void execute() {

        int targetPosition = getTargetPosition();
        switch (targetPosition) {
            case ClimberUp:
                ClimberSubsystem.moveToZero();
                SmartDashboard.putString("Climber Position", "UP");

                break;
            case ClimberDown:
                ClimberSubsystem.moveToCage();
                SmartDashboard.putString("Climber Position", "DOWN");
                break;

            default:
                
                break;
        }

    }

    @Override
    public void end(boolean interrupted) {
        ClimberSubsystem.stop();
    }
}