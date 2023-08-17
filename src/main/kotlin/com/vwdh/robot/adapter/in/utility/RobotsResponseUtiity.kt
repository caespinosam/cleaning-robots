package com.vwdh.robot.adapter.`in`.utility

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse

fun convertResponseIntoPlainString(list: List<RobotMoveResponse>): String {
    val sb = StringBuilder()
    list.forEach { sb.append(it).append("\n") }
    return sb.toString().trimIndent()
}