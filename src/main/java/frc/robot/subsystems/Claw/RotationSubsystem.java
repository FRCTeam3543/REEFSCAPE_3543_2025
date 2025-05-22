package frc.robot.subsystems.Claw;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RotationSubsystem extends SubsystemBase {
    private static SparkMax rotationMotor;
    private final RelativeEncoder encoder;
    private final PIDController pidController;
    private static SparkClosedLoopController closedLoopController;


    public RotationSubsystem() {
        rotationMotor = new SparkMax(10, MotorType.kBrushless);
        closedLoopController = rotationMotor.getClosedLoopController();
        encoder = rotationMotor.getEncoder();

        pidController = new PIDController(0.09, 0.01, 0); // Lower P value for better control
        pidController.setTolerance(0.1); // Set tolerance for better stopping

        // Configure motor
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40) // Set appropriate current limit
                .openLoopRampRate(0.5)
                .idleMode(IdleMode.kBrake)
                .inverted(false) // Make sure this is correct
                        .closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxVelocity(2000) // Tune max velocity
                .maxAcceleration(1000) // Tune max acceleration
                .allowedClosedLoopError(1.0);

        rotationMotor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    }

    /**
     * Stops the rotation motor.
     */
    public static void stop() {
        rotationMotor.disable();
    }

    /**
     * Sets the homing position of the encoder.
     */
    public Command homingPosition() {
        return this.runOnce(() -> encoder.setPosition(0));
    }

    public static void moveToZero() {
        stop();
        closedLoopController.setReference(0.23, ControlType.kPosition, ClosedLoopSlot.kSlot0);

        closedLoopController.setReference(0.23, ControlType.kPosition, ClosedLoopSlot.kSlot1);

    }
    
    /**
     * Moves to coral position 1 (L1 coral reef position).
     */
    public static void moveToCoralPosition1() {
        stop();
        closedLoopController.setReference(2.8, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }

    /**
     * Moves to coral position 2 (L2 and L3 coral reef position).
     */

    public static void elevatorCoralPositionL4() { // move the L4 voral position
        stop();
        closedLoopController.setReference(1.3, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }

    
    public static void moveToIntakingStation() { //move to intake station position
        stop();
        closedLoopController.setReference(5.42, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }

    public static void AlgaePosition() { // moves to algae position
        stop();
        closedLoopController.setReference(3.5, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }

    /**
     * Helper method to move to a preset position using PID control.
     * 
     * @param presetIndex The index of the preset position (0-4).
     */

}