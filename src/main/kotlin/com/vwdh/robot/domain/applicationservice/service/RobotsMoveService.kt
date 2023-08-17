package com.vwdh.robot.domain.applicationservice.service

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveCommand
import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse
import com.vwdh.robot.domain.applicationservice.port.`in`.RobotsMoveUseCase
import java.util.*

class RobotsMoveService(
    private val robotMoveCommandHandler: RobotMoveCommandHandler
) : RobotsMoveUseCase {

    override fun executeRobotMoves(textInput: String): List<RobotMoveResponse> {
        val robotsCommands = buildCommands(textInput)
        val finalPositions = mutableListOf<RobotMoveResponse>()
        robotsCommands.forEach {
            finalPositions.add(robotMoveCommandHandler.moveRobot(it))
        }

        return finalPositions
    }

    private fun buildCommands(textInput: String): List<RobotMoveCommand> {
        val robotsCommands = mutableListOf<RobotMoveCommand>()
        Scanner(textInput).use { reader ->
            val firstLine = reader.nextLine().split(" ")
            val maxPositionX = firstLine[0].toInt()
            val maxPositionY = firstLine[1].toInt()

            while (reader.hasNextLine()) {
                val positionLine = reader.nextLine().split(" ")
                val initPositionX = positionLine[0].toInt()
                val initPositionY = positionLine[1].toInt()
                val initOrientation = positionLine[2]
                val movesLine = reader.nextLine()
                val command = RobotMoveCommand.of(
                    maxPositionX = maxPositionX,
                    maxPositionY = maxPositionY,
                    initPositionX = initPositionX,
                    initPositionY = initPositionY,
                    initOrientation = initOrientation,
                    moves = movesLine
                )

                robotsCommands.add(command)
            }
        }

        return robotsCommands
    }

}