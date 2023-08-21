package com.vwdh.robot.application.service.port.`in`.dto

import com.vwdh.robot.application.model.valueobject.Orientation

data class RobotMoveResponse(
    val finalPositionX: Int,
    val finalPositionY: Int,
    val finalOrientation: Orientation
) {
    override fun toString(): String = "$finalPositionX $finalPositionY $finalOrientation"
}

