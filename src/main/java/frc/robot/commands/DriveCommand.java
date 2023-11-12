package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;

public class DriveCommand extends CommandBase {

    private final SwerveSubsystem subsys;

    private final DoubleSupplier vX, vY;
    private final DoubleSupplier rX, rY;

    public DriveCommand(SwerveSubsystem subsys, DoubleSupplier vX, DoubleSupplier vY, DoubleSupplier rX, DoubleSupplier rY) {
        this.subsys = subsys;

        this.vX = vX;
        this.vY = vY;

        this.rX = rX;
        this.rY = rY;

        addRequirements(this.subsys);
    }

    @Override
    public void execute() {
        // Read from joysticks
        double driveX = vX.getAsDouble();
        double driveY = vY.getAsDouble();
        double rotX = rX.getAsDouble();
        double rotY = rY.getAsDouble();

        ChassisSpeeds speeds = subsys.getTargetSpeeds(driveX, driveY, rotX, rotY);
        // subsys.drive(speeds);

        subsys.driveFieldOriented(speeds);

    }
    
}
