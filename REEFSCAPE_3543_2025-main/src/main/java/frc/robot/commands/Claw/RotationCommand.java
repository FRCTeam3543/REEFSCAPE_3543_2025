package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Claw.RotationSubsystem;

public class RotationCommand extends Command {
    private final RotationSubsystem rotationSubsystem;

    // private final Trigger aButton;
    // private final Trigger bButton;
    // private final Trigger xButton;
    // private final Trigger yButton;

    public RotationCommand(RotationSubsystem rotationSubsystem) {
        this.rotationSubsystem = rotationSubsystem;
        addRequirements(rotationSubsystem);

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
    public static final int NONE = 0;

    int getTargetPosition() {
        if (Constants.OperatorConstants.driverXbox.button(1).getAsBoolean()) {
            return CORAL_1;
        } else if (Constants.OperatorConstants.driverXbox.button(2).getAsBoolean()) {
            return CORAL_2;
        } else if (Constants.OperatorConstants.driverXbox.button(3).getAsBoolean()) {
            return CORAL_3;
        } else if (Constants.OperatorConstants.driverXbox.button(4).getAsBoolean()) {
            return CORAL_4;
        }
        return NONE;
    }

    @Override
    public void execute() {
        int targetPosition = getTargetPosition();
        switch (targetPosition) {
            case CORAL_1:

                rotationSubsystem.moveToZero();
                SmartDashboard.putString("Claw Position", "HOME");

                break;
            case CORAL_2:

                rotationSubsystem.moveToCoralPosition1();
                SmartDashboard.putString("Claw Position", "CORAL");

                break;

            case CORAL_3:
                rotationSubsystem.stop();
                SmartDashboard.putString("Claw Position", "CLAW DOWN");

                break;

            case CORAL_4:
                rotationSubsystem.moveToIntakingStation();
                SmartDashboard.putString("Claw Position", "INTAKE STATION");

                break;

            default:
                // rotationSubsystem.stop();
                break;
        }

        // Log button presses for debugging
        // if (aButton.getAsBoolean()) {
        // System.out.println("L1 ");
        // } else if (bButton.getAsBoolean()) {
        // System.out.println("L2 and L3 ");
        // } else if (xButton.getAsBoolean()) {
        // System.out.println("L4 ");
        // } else if (yButton.getAsBoolean()) {
        // System.out.println("Intake Station ");
        // }
    }

    @Override
    public void end(boolean interrupted) {
        rotationSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}