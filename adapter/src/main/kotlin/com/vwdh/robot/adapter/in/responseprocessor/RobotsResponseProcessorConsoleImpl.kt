package com.vwdh.robot.adapter.`in`.responseprocessor

import com.vwdh.robot.adapter.`in`.utility.convertResponseIntoPlainString
import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveResponse
import org.slf4j.LoggerFactory

class RobotsResponseProcessorConsoleImpl : RobotsResponseProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun process(responses: List<RobotMoveResponse>) {
        val textToShow = convertResponseIntoPlainString(responses)
        logger.info("OUTPUT:\n{}", textToShow)
    }
}