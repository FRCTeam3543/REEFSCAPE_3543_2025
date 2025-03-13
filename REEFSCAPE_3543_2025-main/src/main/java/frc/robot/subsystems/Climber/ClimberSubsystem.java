
package frc.robot.subsystems.Climber;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {

    private final SparkMax climberMotor;
    private SparkClosedLoopController closedLoopController;

    public ClimberSubsystem() {
        climberMotor = new SparkMax(13, MotorType.kBrushless); // Use correct CAN ID if needed
    
        closedLoopController = climberMotor.getClosedLoopController();

        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40)
                .openLoopRampRate(0.5)
                .idleMode(IdleMode.kBrake)
                .inverted(false).closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).maxMotion.maxVelocity(2000)
                .maxAcceleration(1000)
                .allowedClosedLoopError(1.0);

        climberMotor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runIntake(double speed) {
        climberMotor.set(speed);
    }

    public void stop() {
        climberMotor.set(0);
    }

    public void moveToZero() {
        stop();
        closedLoopController.setReference(0, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }


    public void motorForawrds(){
        climberMotor.set(1);
    }
    /**
     * Moves to coral position 1 (L1 coral reef position).
     */
    public void moveToCage() {
        stop();
        closedLoopController.setReference(180, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }
}