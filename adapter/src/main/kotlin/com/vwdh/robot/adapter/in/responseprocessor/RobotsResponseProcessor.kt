package com.vwdh.robot.adapter.`in`.responseprocessor

import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveResponse

interface RobotsResponseProcessor {
    fun process(responses: List<RobotMoveResponse>)
}