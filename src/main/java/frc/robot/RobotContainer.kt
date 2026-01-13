// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import frc.robot.commands.*
import frc.robot.subsystems.Drivetrain
import frc.robot.subsystems.FuelSubsystem
import frc.robot.Constants.OperatorConstants

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the [Robot] periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and trigger mappings) should be declared here.
 */
class RobotContainer {
    // The robot's subsystems
    private val driveSubsystem = Drivetrain()
    private val fuelSubsystem = FuelSubsystem()

    // The driver's controller
    private val driverController = CommandXboxController(
        OperatorConstants.DRIVER_CONTROLLER_PORT
    )

    // The operator's controller
    private val operatorController = CommandXboxController(
        OperatorConstants.OPERATOR_CONTROLLER_PORT
    )

    // The autonomous chooser
    private val autoChooser = SendableChooser<Command?>()

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    init {
        configureBindings()

        // Set the options to show up in the Dashboard for selecting auto modes. If you
        // add additional auto modes you can add additional lines here with
        // autoChooser.addOption
        autoChooser.setDefaultOption("Autonomous", ExampleAuto(driveSubsystem, fuelSubsystem))
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the [Trigger.Trigger]
     * constructor with an arbitrary predicate, or via the named factories in
     * [edu.wpi.first.wpilibj2.command.button.CommandGenericHID]'s subclasses
     * for [Xbox][CommandXboxController]/
     * [PS4][edu.wpi.first.wpilibj2.command.button.CommandPS4Controller]
     * controllers or
     * [Flight][edu.wpi.first.wpilibj2.command.button.CommandJoystick].
     */
    private fun configureBindings() {
        // While the left bumper on operator controller is held, intake Fuel

        operatorController.leftBumper().whileTrue(Intake(fuelSubsystem))
        // While the right bumper on the operator controller is held, spin up for 1
        // second, then launch fuel. When the button is released, stop.
        operatorController.rightBumper().whileTrue(LaunchSequence(fuelSubsystem))
        // While the A button is held on the operator controller, eject fuel back out
        // the intake
        operatorController.a().whileTrue(Eject(fuelSubsystem))

        // Set the default command for the drive subsystem to the command provided by
        // factory with the values provided by the joystick axes on the driver
        // controller. The Y axis of the controller is inverted so that pushing the
        // stick away from you (a negative value) drives the robot forwards (a positive
        // value)
        driveSubsystem.setDefaultCommand(Drive(driveSubsystem, driverController))

        fuelSubsystem.setDefaultCommand(fuelSubsystem.run(Runnable { fuelSubsystem.stop() }))
    }

    val autonomousCommand: Command?
        /**
         * Use this to pass the autonomous command to the main [Robot] class.
         *
         * @return the command to run in autonomous
         */
        get() =// An example command will be run in autonomous
            autoChooser.getSelected()
}