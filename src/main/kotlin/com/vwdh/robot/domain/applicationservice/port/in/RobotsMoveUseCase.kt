package com.vwdh.robot.domain.applicationservice.port.`in`

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse

interface RobotsMoveUseCase {
    fun executeRobotMoves(textInput: String): List<RobotMoveResponse>
}