package com.vwdh.robot.application.service.service.factory

import com.vwdh.robot.application.model.entity.Floor
import com.vwdh.robot.application.model.valueobject.FloorId
import java.util.*

class NoObstaclesFloorFactory : FloorFactory {

    override fun build(maxPositionX: Int, maxPositionY: Int) =
        Floor(FloorId(UUID.randomUUID()), maxPositionX, maxPositionY)

}