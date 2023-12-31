package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveCommand extends CommandBase {

    private final SwerveSubsystem subsys;

    private final DoubleSupplier vX, vY;
    private final DoubleSupplier rotationSpeed;

    public DriveCommand(SwerveSubsystem subsys, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier rotationSpeed) {
        this.subsys = subsys;

        this.vX = vX;
        this.vY = vY;

        this.rotationSpeed = rotationSpeed;

        addRequirements(this.subsys);
    }

    @Override
    public void execute() {
        // Read from joysticks
        double driveY = Math.pow(vY.getAsDouble(), 1) * OperatorConstants.JOYSTICK_SCALE;
        double driveX = Math.pow(vX.getAsDouble(), 1) * OperatorConstants.JOYSTICK_SCALE;
        double rotation = rotationSpeed.getAsDouble();

        Translation2d translation = new Translation2d(driveX * subsys.maximumSpeed, driveY * subsys.maximumSpeed);

        subsys.drive(translation, rotation * Constants.MAX_ANGULAR_VELOCITY, true);
    }
}
