package com.vwdh.robot.domain.core.event

import com.vwdh.robot.domain.core.entity.Robot

data class RobotMovedEvent(
    private val robot: Robot
) {
    fun getFinalOrientation() = robot.currentOrientation
    fun getFinalPosition() = robot.currentPosition
}