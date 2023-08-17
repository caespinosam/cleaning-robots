package com.vwdh.robot.domain.applicationservice.factory

import com.vwdh.robot.domain.core.entity.Floor

interface FloorFactory {

    fun build(maxPositionX: Int, maxPositionY: Int): Floor

}