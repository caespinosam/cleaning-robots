package com.vwdh.robot.domain.applicationservice.service

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveCommand
import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse
import com.vwdh.robot.domain.applicationservice.factory.FloorFactory
import com.vwdh.robot.domain.core.RobotDomainService
import com.vwdh.robot.domain.core.entity.Robot
import com.vwdh.robot.domain.core.exception.InvalidConstantValueException
import com.vwdh.robot.domain.core.valueobject.Move
import com.vwdh.robot.domain.core.valueobject.Orientation
import com.vwdh.robot.domain.core.valueobject.Position

class RobotMoveCommandHandler(
    private val robotDomainService: RobotDomainService,
    private val floorFactory: FloorFactory
) {

    fun moveRobot(robotMoveCommand: RobotMoveCommand): RobotMoveResponse {
        try {
            val robot = Robot.of(
                floorFactory.build(robotMoveCommand.maxPositionX, robotMoveCommand.maxPositionY),
                Orientation.valueOf(robotMoveCommand.initOrientation),
                Position.of(robotMoveCommand.initPositionX, robotMoveCommand.initPositionY)
            )
            val moves = robotMoveCommand.moves
                .split("")
                .filter { it.isNotBlank() }
                .map { Move.valueOf(it) }
            val responseEvent = robotDomainService.executeMoves(robot, moves)

            return RobotMoveResponse(
                finalOrientation = responseEvent.getFinalOrientation(),
                finalPositionX = responseEvent.getFinalPosition().x,
                finalPositionY = responseEvent.getFinalPosition().y
            )
        } catch (e: IllegalArgumentException) {
            throw InvalidConstantValueException("Invalid value set to a move or to an orientation: ${e.message}")
        }
    }

}