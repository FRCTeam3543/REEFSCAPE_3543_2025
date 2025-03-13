package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Claw.IntakeSubsystem;
import frc.robot.Constants;

public class IntakeCommand extends Command {

    private final IntakeSubsystem intakeSubsystem;

    public IntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem); // Important so the scheduler handles conflicts
    }

    @Override
    public void execute() {
        double leftTriggerAxis = Constants.OperatorConstants.driverXbox.getLeftTriggerAxis();
        double rightTriggerAxis = Constants.OperatorConstants.driverXbox.getRightTriggerAxis();

        boolean wantsToLaunch = rightTriggerAxis > 0.3;
        boolean wantsToIntake = leftTriggerAxis > 0.3;
        // if the intake switch is on, we can only initialize a launch
        if (wantsToLaunch) {
            intakeSubsystem.launch();
            SmartDashboard.putString("Intake Status", "LAUNCHING CORAL");

        } else if (!intakeSubsystem.isHoldingCoral() && wantsToIntake) {
            intakeSubsystem.intake();
            SmartDashboard.putString("Intake Status", "INTAKING CORAL");

        } else {
            intakeSubsystem.stop();
            SmartDashboard.putString("Intake Status", "NO CORAL");

        }
        // if (rightTriggerAxis > 0.3 && intakeSubsystem.isInputSwitchOn()) {
        // intakeSubsystem.runIntake(0.5);
        // } else if (leftTriggerAxis > 0.3) {
        // intakeSubsystem.runIntake(-0.7);
        // } else {
        // intakeSubsystem.stop();
        // }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }
}