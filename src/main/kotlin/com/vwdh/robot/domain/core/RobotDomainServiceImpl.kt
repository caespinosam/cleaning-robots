package com.vwdh.robot.domain.core

import com.vwdh.robot.domain.core.entity.Robot
import com.vwdh.robot.domain.core.event.RobotMovedEvent
import com.vwdh.robot.domain.core.valueobject.Move

class RobotDomainServiceImpl : RobotDomainService {

    override fun executeMoves(robot: Robot, moves: List<Move>): RobotMovedEvent {
        robot.validateStartPosition()
        moves.forEach {
            when (it) {
                Move.M -> {
                    robot.moveForward()
                }

                Move.L -> {
                    robot.rotate90DegreesLeft()
                }

                Move.R -> {
                    robot.rotate90DegreesRight()
                }
            }
        }

        return RobotMovedEvent(robot)
    }

}
