package com.vwdh.robot.application.model.valueobject

data class Position(
    val x: Int,
    val y: Int
) {
    companion object {
        fun of(x: Int, y: Int) = Position(x, y)
    }
}