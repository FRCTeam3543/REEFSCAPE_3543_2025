package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.Claw.RotationSubsystem;

public class ElevatorCommand extends Command {
    private final ElevatorSubsystem ElevatorSubsystem;

    public ElevatorCommand(ElevatorSubsystem ElevatorSubsystem) {
        this.ElevatorSubsystem = ElevatorSubsystem;
        addRequirements(ElevatorSubsystem);

        // Initialize triggers properly
        // aButton = new Trigger(() ->
        // Constants.OperatorConstants.operatorXbox.button(1).getAsBoolean());
        // bButton = new Trigger(() ->
        // Constants.OperatorConstants.operatorXbox.button(2).getAsBoolean());
        // xButton = new Trigger(() ->
        // Constants.OperatorConstants.operatorXbox.button(3).getAsBoolean());
        // yButton = new Trigger(() ->
        // Constants.OperatorConstants.operatorXbox.button(4).getAsBoolean());

        // Bind triggers to commands
        // aButton.onTrue(new InstantCommand(rotationSubsystem::moveToCoralPosition1));
        // bButton.onTrue(new InstantCommand(rotationSubsystem::moveToCoralPosition2));
        // xButton.onTrue(new InstantCommand(rotationSubsystem::moveToCoralPosition3));
        // yButton.onTrue(new InstantCommand(rotationSubsystem::moveToIntakingStation));

    }

    public static final int CORAL_1 = 1;
    public static final int CORAL_2 = 2;
    public static final int CORAL_3 = 3;
    public static final int CORAL_4 = 4;
    public static final int CORAL_5 = 5;
    public static final int CORAL_6 = 6;
    public static final int CORAL_7 = 7;
    public static final int NONE = 0;

    int getTargetPosition() {

        if (Constants.OperatorConstants.operatorXbox.button(1).getAsBoolean()) {
            return CORAL_1;
        } else if (Constants.OperatorConstants.operatorXbox.button(2).getAsBoolean()) {
            return CORAL_2;
        } else if (Constants.OperatorConstants.operatorXbox.button(3).getAsBoolean()) {
            return CORAL_3;
        } else if (Constants.OperatorConstants.operatorXbox.button(4).getAsBoolean()) {
            return CORAL_4;
        } else if (Constants.OperatorConstants.operatorXbox.button(6).getAsBoolean()) {
            return CORAL_5;
        } else if (Constants.OperatorConstants.operatorXbox.button(7).getAsBoolean()) {
            return CORAL_6;
        } else if (Constants.OperatorConstants.operatorXbox.button(8).getAsBoolean()) {
            return CORAL_7;
        }
        return NONE;
    }

    @Override
    public void execute() {
        int targetPosition = getTargetPosition();
        switch (targetPosition) {
            case CORAL_1:
                ElevatorSubsystem.moveToCoralL2();
                RotationSubsystem.moveToCoralPosition1();
                SmartDashboard.putString("Elevator Position", "REEF L2");
                break;
            //Reef Position L2
            case CORAL_2:
                ElevatorSubsystem.moveToCoralL3();
                RotationSubsystem.moveToCoralPosition1();
                SmartDashboard.putString("Elevator Position", "REEF L3");
                break;
            // Reef Position L3
            case CORAL_3:
                ElevatorSubsystem.moveToCoralL4();
                RotationSubsystem.elevatorCoralPositionL4();
                SmartDashboard.putString("Elevator Position", "REEF L4");
                break;
            // Reef Position L4
            case CORAL_4:
                ElevatorSubsystem.stop();
                SmartDashboard.putString("Elevator Position", "STOP");
                break;
            // Disable Elevator Motors
            case CORAL_5:
                ElevatorSubsystem.moveDown();
                RotationSubsystem.moveToZero();
                SmartDashboard.putString("Elevator Position", "REEF L1");
                break;
            // Reef Position L1
            case CORAL_6:
                ElevatorSubsystem.moveToAlgaeP1();
                RotationSubsystem.AlgaePosition();
                SmartDashboard.putString("Elevator Position", "ALGAE 1");
                break;
            // Reef Position Algae Level 1
            case CORAL_7:
                ElevatorSubsystem.moveToAlgaeP2();
                RotationSubsystem.AlgaePosition();
                SmartDashboard.putString("Elevator Position", "ALGAE 2");
                break;
            // Reef postition Algae Level 2
            default:
                // rotationSubsystem.stop();
                break;
        }

    }

    @Override
    public void end(boolean interrupted) {
        ElevatorSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}