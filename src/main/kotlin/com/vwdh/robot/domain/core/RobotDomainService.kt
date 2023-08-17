package com.vwdh.robot.domain.core

import com.vwdh.robot.domain.core.entity.Robot
import com.vwdh.robot.domain.core.event.RobotMovedEvent
import com.vwdh.robot.domain.core.valueobject.Move

interface RobotDomainService {
    fun executeMoves(robot: Robot, moves: List<Move>): RobotMovedEvent
}