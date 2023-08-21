package com.vwdh.robot.application.model

import com.vwdh.robot.application.model.entity.Robot
import com.vwdh.robot.application.model.event.RobotMovedEvent
import com.vwdh.robot.application.model.valueobject.Move

interface RobotDomainService {
    fun executeMoves(robot: Robot, moves: List<Move>): RobotMovedEvent
}