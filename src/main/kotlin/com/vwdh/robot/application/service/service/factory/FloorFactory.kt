package com.vwdh.robot.application.service.service.factory

import com.vwdh.robot.application.model.entity.Floor

interface FloorFactory {

    fun build(maxPositionX: Int, maxPositionY: Int): Floor

}