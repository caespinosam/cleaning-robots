package com.vwdh.robot.adapter.`in`

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse

interface RobotsResponseProcessor {
    fun process(responses: List<RobotMoveResponse>)
}