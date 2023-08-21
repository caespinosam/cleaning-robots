package com.vwdh.robot.application.service.port.`in`

import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveResponse

interface RobotsMoveUseCase {
    fun executeRobotMoves(textInput: String): List<RobotMoveResponse>
}