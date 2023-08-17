package com.vwdh.robot.adapter.`in`

import com.vwdh.robot.adapter.`in`.utility.convertResponseIntoPlainString
import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse
import org.slf4j.LoggerFactory

class RobotsResponseProcessorConsoleImpl : RobotsResponseProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun process(responses: List<RobotMoveResponse>) {
        val textToShow = convertResponseIntoPlainString(responses)
        logger.info("OUTPUT:\n{}", textToShow)
    }
}