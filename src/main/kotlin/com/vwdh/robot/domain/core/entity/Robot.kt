package com.vwdh.robot.domain.core.entity


import com.vwdh.robot.domain.core.exception.MoveNotWithinTheFloorLimitsException
import com.vwdh.robot.domain.core.valueobject.Orientation
import com.vwdh.robot.domain.core.valueobject.Position
import com.vwdh.robot.domain.core.valueobject.RobotId
import java.util.*

class Robot(
    id: RobotId,
    val floor: Floor,
    var currentOrientation: Orientation,
    var currentPosition: Position
) : AggregateRoot<RobotId>(id) {

    companion object {
        fun of(floor: Floor, orientation: Orientation, position: Position) = Robot(
            RobotId(UUID.randomUUID()),
            floor,
            orientation,
            position
        )
    }

    fun rotate90DegreesLeft() {
        currentOrientation = when (currentOrientation) {
            Orientation.N -> Orientation.W
            Orientation.W -> Orientation.S
            Orientation.S -> Orientation.E
            Orientation.E -> Orientation.N
        }
    }

    fun rotate90DegreesRight() {
        currentOrientation = when (currentOrientation) {
            Orientation.N -> Orientation.E
            Orientation.E -> Orientation.S
            Orientation.S -> Orientation.W
            Orientation.W -> Orientation.N
        }
    }

    fun moveForward() {
        var tmpX = currentPosition.x
        var tmpY = currentPosition.y
        when (currentOrientation) {
            Orientation.N -> tmpY = currentPosition.y + 1
            Orientation.E -> tmpX = currentPosition.x + 1
            Orientation.S -> tmpY = currentPosition.y - 1
            Orientation.W -> tmpX = currentPosition.x - 1
        }

        validateNewPosition(tmpX, tmpY)
        currentPosition = Position.of(tmpX, tmpY)
    }

    fun validateStartPosition() {
        if (!(currentPosition.x in 0..floor.maxPositionX && currentPosition.y in 0..floor.maxPositionY)) {
            throw MoveNotWithinTheFloorLimitsException("The start position is invalid")

        }
    }

    private fun validateNewPosition(positionX: Int, positionY: Int) {
        if (!(positionX in 0..floor.maxPositionX && positionY in 0..floor.maxPositionY)) {
            throw MoveNotWithinTheFloorLimitsException("The requested move is not within the floor limits")

        }
    }
}