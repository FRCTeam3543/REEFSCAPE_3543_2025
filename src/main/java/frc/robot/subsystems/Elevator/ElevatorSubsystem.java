package frc.robot.subsystems.Elevator;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

    private final SparkFlex elevatorMotorLeft;
    private final SparkFlex elevatorMotorRight;
    private final PIDController pidController;
    private final RelativeEncoder leftEncoder;
    private SparkClosedLoopController leftClosedLoopController;

    public ElevatorSubsystem() {
        
        elevatorMotorLeft = new SparkFlex(12, MotorType.kBrushless);
        elevatorMotorRight = new SparkFlex(11, MotorType.kBrushless);

        elevatorMotorRight.getEncoder();
        leftEncoder = elevatorMotorLeft.getEncoder();

        // Get closed-loop controller
        leftClosedLoopController = elevatorMotorLeft.getClosedLoopController();

        // PID Controller for manual control
        pidController = new PIDController(0.1, 0.01, 0); // Tune values as needed
        pidController.setTolerance(0.1);

        // Left motor configuration
        SparkFlexConfig leftconfig = new SparkFlexConfig();
        leftconfig.smartCurrentLimit(40)
                .openLoopRampRate(0.5)
                .idleMode(IdleMode.kBrake)
                .inverted(false) // Ensure correct inversion
                .closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .maxMotion.maxVelocity(2000)
                .maxAcceleration(1000)
                .allowedClosedLoopError(1.0);

        // Right motor configuration (inverted)
        SparkFlexConfig rightconfig = new SparkFlexConfig();
        rightconfig.smartCurrentLimit(40)
                .openLoopRampRate(0.5)
                .idleMode(IdleMode.kBrake)
                .inverted(true) // Ensure proper inversion
                        .closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxVelocity(2000)
                .maxAcceleration(1000)
                .allowedClosedLoopError(1.0);


        // Apply configurations to motors
        elevatorMotorLeft.configure(leftconfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        elevatorMotorRight.configure(rightconfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void runIntake(double speed) {
        elevatorMotorLeft.set(speed);

    }

    public void stop() {
        elevatorMotorLeft.disable();

    }

    public Command HomingPosition() {
        return this.runOnce(() -> leftEncoder.setPosition(0.0));
    }

    public void moveToCoralL2() {
        stop();
        leftClosedLoopController.setReference(3.5, ControlType.kPosition, ClosedLoopSlot.kSlot0);    }


    public void moveDown() {
       elevatorMotorLeft.set(-0.13);
    }
    /**
     * Moves to coral position 1 (L1 coral reef position).
     */
    public void moveToCoralL3() {
        stop();
        leftClosedLoopController.setReference(10, ControlType.kPosition, ClosedLoopSlot.kSlot0);

    }

    public void moveToCoralL4() {
        stop();
        leftClosedLoopController.setReference(22.7, ControlType.kPosition, ClosedLoopSlot.kSlot0);

    }

    public void moveToAlgaeP1() {
        stop();
        leftClosedLoopController.setReference(6.85, ControlType.kPosition, ClosedLoopSlot.kSlot0);

    }

    public void moveToAlgaeP2() {
        stop();
        leftClosedLoopController.setReference(13.68, ControlType.kPosition, ClosedLoopSlot.kSlot0);

    }
    
}