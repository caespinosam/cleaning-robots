package com.vwdh.robot.domain.core.entity

import com.vwdh.robot.domain.core.valueobject.FloorId

class Floor(
    id: FloorId,
    val maxPositionX: Int,
    val maxPositionY: Int

) : BaseEntity<FloorId>(id)