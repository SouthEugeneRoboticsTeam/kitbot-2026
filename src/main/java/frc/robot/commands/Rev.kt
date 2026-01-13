// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import frc.robot.Constants.FuelConstants
import frc.robot.subsystems.FuelSubsystem

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
class Rev(fuelSystem: FuelSubsystem) : Command() {
    /** Creates a new Intake.  */
    var fuelSubsystem: FuelSubsystem

    init {
        addRequirements(fuelSystem)
        this.fuelSubsystem = fuelSystem
    }

    // Called when the command is initially scheduled. Set the rollers to the
    // appropriate values for intaking
    override fun initialize() {
        fuelSubsystem
            .setFlywheel(
                SmartDashboard.getNumber("Launching launcher roller value", FuelConstants.LAUNCHING_LAUNCHER_VOLTAGE)
            )
        fuelSubsystem.setFeederRoller(
            SmartDashboard.getNumber(
                "Launching spin-up feeder value",
                FuelConstants.SPIN_UP_FEEDER_VOLTAGE
            )
        )
    }

    // Called every time the scheduler runs while the command is scheduled. This
    // command doesn't require updating any values while running
    override fun execute() {
    }

    // Called once the command ends or is interrupted. Stop the rollers
    override fun end(interrupted: Boolean) {
    }

    // Returns true when the command should end.
    override fun isFinished(): Boolean {
        return false
    }
}