package com.vwdh.robot.domain.applicationservice.dto

data class RobotMoveCommand(
    val maxPositionX: Int,
    val maxPositionY: Int,
    val initPositionX: Int,
    val initPositionY: Int,
    val initOrientation: String,
    val moves: String
) {

    companion object {
        fun of(
            maxPositionX: Int,
            maxPositionY: Int,
            initPositionX: Int,
            initPositionY: Int,
            initOrientation: String,
            moves: String
        ) =
            RobotMoveCommand(maxPositionX, maxPositionY, initPositionX, initPositionY, initOrientation, moves)
    }
}