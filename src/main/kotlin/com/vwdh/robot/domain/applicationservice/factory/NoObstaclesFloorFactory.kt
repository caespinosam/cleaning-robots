package com.vwdh.robot.domain.applicationservice.factory

import com.vwdh.robot.domain.core.entity.Floor
import com.vwdh.robot.domain.core.valueobject.FloorId
import java.util.*

class NoObstaclesFloorFactory : FloorFactory {

    override fun build(maxPositionX: Int, maxPositionY: Int) =
        Floor(FloorId(UUID.randomUUID()), maxPositionX, maxPositionY)

}