package com.vwdh.robot.domain.applicationservice.dto

import com.vwdh.robot.domain.core.valueobject.Orientation

data class RobotMoveResponse(
    val finalPositionX: Int,
    val finalPositionY: Int,
    val finalOrientation: Orientation
) {
    override fun toString(): String = "$finalPositionX $finalPositionY $finalOrientation"
}

