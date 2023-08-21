package com.vwdh.robot.application.model

import com.vwdh.robot.application.model.entity.Robot
import com.vwdh.robot.application.model.event.RobotMovedEvent
import com.vwdh.robot.application.model.valueobject.Move

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
