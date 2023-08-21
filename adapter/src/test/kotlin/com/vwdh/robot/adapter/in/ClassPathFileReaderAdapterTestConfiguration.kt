package com.vwdh.robot.adapter.`in`

import com.vwdh.robot.application.model.RobotDomainService
import com.vwdh.robot.application.model.RobotDomainServiceImpl
import com.vwdh.robot.application.service.port.`in`.RobotsMoveUseCase
import com.vwdh.robot.application.service.service.RobotMoveCommandHandler
import com.vwdh.robot.application.service.service.RobotsMoveService
import com.vwdh.robot.application.service.service.factory.FloorFactory
import com.vwdh.robot.application.service.service.factory.NoObstaclesFloorFactory
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean

@SpringBootConfiguration
class ClassPathFileReaderAdapterTestConfiguration {

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

}