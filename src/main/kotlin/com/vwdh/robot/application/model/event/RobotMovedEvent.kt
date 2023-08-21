package com.vwdh.robot.application.model.event

import com.vwdh.robot.application.model.entity.Robot

data class RobotMovedEvent(
    private val robot: Robot
) {
    fun getFinalOrientation() = robot.currentOrientation
    fun getFinalPosition() = robot.currentPosition
}