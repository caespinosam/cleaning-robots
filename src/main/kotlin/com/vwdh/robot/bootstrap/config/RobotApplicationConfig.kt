package com.vwdh.robot.bootstrap.config

import com.vwdh.robot.adapter.`in`.RobotsResponseProcessor
import com.vwdh.robot.adapter.`in`.RobotsResponseProcessorConsoleImpl
import com.vwdh.robot.domain.applicationservice.factory.FloorFactory
import com.vwdh.robot.domain.applicationservice.factory.NoObstaclesFloorFactory
import com.vwdh.robot.domain.applicationservice.port.`in`.RobotsMoveUseCase
import com.vwdh.robot.domain.applicationservice.service.RobotMoveCommandHandler
import com.vwdh.robot.domain.applicationservice.service.RobotsMoveService
import com.vwdh.robot.domain.core.RobotDomainService
import com.vwdh.robot.domain.core.RobotDomainServiceImpl
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