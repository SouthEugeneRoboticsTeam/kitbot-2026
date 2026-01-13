// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems

import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants.DriveConstants

class Drivetrain : SubsystemBase() {
    private val leftLeader = TalonFX(DriveConstants.LEFT_LEADER_ID)
    private val leftFollower = TalonFX(DriveConstants.LEFT_FOLLOWER_ID)
    private val rightLeader = TalonFX(DriveConstants.RIGHT_LEADER_ID)
    private val rightFollower = TalonFX(DriveConstants.RIGHT_FOLLOWER_ID)

    private val drive: DifferentialDrive

    init {

        // set up differential drive class
        drive = DifferentialDrive(leftLeader, rightLeader)

        // Create the configuration to apply to motors. Voltage compensation
        // helps the robot perform more similarly on different
        // battery voltages (at the cost of a little bit of top speed on a fully charged
        // battery). The current limit helps prevent tripping
        // breakers.
        val config: SparkMaxConfig = SparkMaxConfig()
        config.voltageCompensation(12)
        config.smartCurrentLimit(DRIVE_MOTOR_CURRENT_LIMIT)

        // Set configuration to follow each leader and then apply it to corresponding
        // follower. Resetting in case a new controller is swapped
        // in and persisting in case of a controller reset due to breaker trip
        config.follow(leftLeader)
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
        config.follow(rightLeader)
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)

        // Remove following, then apply config to right leader
        config.disableFollowerMode()
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
        // Set config to inverted and then apply to left leader. Set Left side inverted
        // so that postive values drive both sides forward
        config.inverted(true)
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)
    }

    override fun periodic() {
    }

    fun driveArcade(xSpeed: Double, zRotation: Double) {
        drive.arcadeDrive(xSpeed, zRotation)
    }
}