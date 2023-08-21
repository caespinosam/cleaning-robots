package com.vwdh.robot.application.model.entity

import com.vwdh.robot.application.model.valueobject.FloorId

class Floor(
    id: FloorId,
    val maxPositionX: Int,
    val maxPositionY: Int

) : BaseEntity<FloorId>(id)