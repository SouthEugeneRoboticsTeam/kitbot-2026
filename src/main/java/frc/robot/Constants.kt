// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 *
 *
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
class Constants {
    object DriveConstants {
        // Motor controller IDs for drivetrain motors
        const val LEFT_LEADER_ID: Int = 1
        const val LEFT_FOLLOWER_ID: Int = 2
        const val RIGHT_LEADER_ID: Int = 3
        const val RIGHT_FOLLOWER_ID: Int = 4

        // Current limit for drivetrain motors. 60A is a reasonable maximum to reduce
        // likelihood of tripping breakers or damaging CIM motors
        const val DRIVE_MOTOR_CURRENT_LIMIT: Int = 60
    }

    object FuelConstants {
        // Motor controller IDs for Fuel Mechanism motors
        const val FEEDER_MOTOR_ID: Int = 6
        const val INTAKE_LAUNCHER_MOTOR_ID: Int = 5

        // Current limit and nominal voltage for fuel mechanism motors.
        const val FEEDER_MOTOR_CURRENT_LIMIT: Int = 60
        const val LAUNCHER_MOTOR_CURRENT_LIMIT: Int = 60

        // Voltage values for various fuel operations. These values may need to be tuned
        // based on exact robot construction.
        // See the Software Guide for tuning information
        val INTAKING_FEEDER_VOLTAGE: Double = -12.0
        const val INTAKING_INTAKE_VOLTAGE: Double = 10.0
        const val LAUNCHING_FEEDER_VOLTAGE: Double = 9.0
        const val LAUNCHING_LAUNCHER_VOLTAGE: Double = 10.6
        val SPIN_UP_FEEDER_VOLTAGE: Double = -6.0
        const val SPIN_UP_SECONDS: Double = 1.0
    }

    object OperatorConstants {
        // Port constants for driver and operator controllers. These should match the
        // values in the Joystick tab of the Driver Station software
        const val DRIVER_CONTROLLER_PORT: Int = 0
        const val OPERATOR_CONTROLLER_PORT: Int = 1

        // This value is multiplied by the joystick value when rotating the robot to
        // help avoid turning too fast and beign difficult to control
        const val DRIVE_SCALING: Double = .7
        const val ROTATION_SCALING: Double = .8
    }
}