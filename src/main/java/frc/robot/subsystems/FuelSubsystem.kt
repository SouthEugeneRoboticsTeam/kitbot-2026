// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems

import com.ctre.phoenix6.configs.TalonFXConfiguration
import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants.FuelConstants

class FuelSubsystem : SubsystemBase() {
    private val feederRoller = TalonFX(FuelConstants.FEEDER_MOTOR_ID)
    private val intakeLauncherRoller = TalonFX(FuelConstants.INTAKE_LAUNCHER_MOTOR_ID)

    /** Creates a new CANBallSubsystem.  */
    init {

        // create the configuration for the feeder roller, set a current limit and apply
        // the config to the controller
        val feederConfig = TalonFXConfiguration()
        // feederConfig.withCurrentLimits()
        // feederRoller.configurator() // TODO: what
        // create the configuration for the launcher roller, set a current limit, set
        // the motor to inverted so that positive values are used for both intaking and
        // launching, and apply the config to the controller
        val launcherConfig = TalonFXConfiguration()
        // launcherConfig.withCurrentLimits(CuLAUNCHER_MOTOR_CURRENT_LIMIT)
        // intakeLauncherRoller.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters)

        // put default values for various fuel operations onto the dashboard
        // all commands using this subsystem pull values from the dashbaord to allow
        // you to tune the values easily, and then replace the values in Constants.java
        // with your new values. For more information, see the Software Guide.
        SmartDashboard.putNumber("Intaking feeder roller value", FuelConstants.INTAKING_FEEDER_VOLTAGE)
        SmartDashboard.putNumber("Intaking intake roller value", FuelConstants.INTAKING_INTAKE_VOLTAGE)
        SmartDashboard.putNumber("Launching feeder roller value", FuelConstants.LAUNCHING_FEEDER_VOLTAGE)
        SmartDashboard.putNumber("Launching launcher roller value", FuelConstants.LAUNCHING_LAUNCHER_VOLTAGE)
        SmartDashboard.putNumber("Spin-up feeder roller value", FuelConstants.SPIN_UP_FEEDER_VOLTAGE)
    }

    // A method to set the voltage of the intake roller
    fun setFlywheel(voltage: Double) {
        intakeLauncherRoller.setVoltage(voltage)
    }

    // A method to set the voltage of the intake roller
    fun setFeederRoller(voltage: Double) {
        feederRoller.setVoltage(voltage)
    }

    // A method to stop the rollers
    fun stop() {
        feederRoller.stopMotor()
        intakeLauncherRoller.stopMotor()
    }

    override fun periodic() {
        // This method will be called once per scheduler run
    }
}