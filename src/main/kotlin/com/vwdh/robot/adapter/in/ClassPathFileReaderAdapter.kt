package com.vwdh.robot.adapter.`in`

import com.vwdh.robot.domain.applicationservice.port.`in`.RobotsMoveUseCase
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class ClassPathFileReaderAdapter(
    private val robotsMoveUseCase: RobotsMoveUseCase,
    private val robotsResponseProcessor: RobotsResponseProcessor
) : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String) {
        val resource = ClassPathResource(
            "data/robot_input.txt"
        ).inputStream

        BufferedReader(
            InputStreamReader(resource)
        ).use { reader ->
            val inputText = reader.readText()
            logger.info("INPUT:\n {}", inputText)
            val response = robotsMoveUseCase.executeRobotMoves(inputText)
            robotsResponseProcessor.process(response)
        }
    }
}