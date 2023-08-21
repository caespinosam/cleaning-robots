package com.vwdh.robot.bootstrap.config

import com.vwdh.robot.adapter.`in`.responseprocessor.RobotsResponseProcessor
import com.vwdh.robot.adapter.`in`.responseprocessor.RobotsResponseProcessorConsoleImpl
import com.vwdh.robot.application.service.service.factory.FloorFactory
import com.vwdh.robot.application.service.service.factory.NoObstaclesFloorFactory
import com.vwdh.robot.application.service.port.`in`.RobotsMoveUseCase
import com.vwdh.robot.application.service.service.RobotMoveCommandHandler
import com.vwdh.robot.application.service.service.RobotsMoveService
import com.vwdh.robot.application.model.RobotDomainService
import com.vwdh.robot.application.model.RobotDomainServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RobotApplicationConfig {

    @Bean
    fun robotDomainService(): RobotDomainService {
        return RobotDomainServiceImpl()
    }

    @Bean
    fun floorFactory(): FloorFactory {
        return NoObstaclesFloorFactory()
    }

    @Bean
    fun robotMoveCommandHandler(
        robotDomainService: RobotDomainService,
        floorFactory: FloorFactory
    ): RobotMoveCommandHandler {
        return RobotMoveCommandHandler(robotDomainService, floorFactory)
    }

    @Bean
    fun robotsMoveUseCase(
        robotMoveCommandHandler: RobotMoveCommandHandler
    ): RobotsMoveUseCase {
        return RobotsMoveService(robotMoveCommandHandler)
    }

    @Bean
    fun robotsResponseProcessor(): RobotsResponseProcessor {
        return RobotsResponseProcessorConsoleImpl()
    }
}