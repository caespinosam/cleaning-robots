package com.vwdh.robot.application.service.service

import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveCommand
import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveResponse
import com.vwdh.robot.application.service.service.factory.FloorFactory
import com.vwdh.robot.application.model.RobotDomainService
import com.vwdh.robot.application.model.entity.Robot
import com.vwdh.robot.application.model.exception.InvalidConstantValueException
import com.vwdh.robot.application.model.valueobject.Move
import com.vwdh.robot.application.model.valueobject.Orientation
import com.vwdh.robot.application.model.valueobject.Position

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