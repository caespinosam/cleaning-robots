package com.vwdh.robot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RobotSpringBootApplication

fun main(args: Array<String>) {
    runApplication<com.vwdh.robot.RobotSpringBootApplication>(*args)
}