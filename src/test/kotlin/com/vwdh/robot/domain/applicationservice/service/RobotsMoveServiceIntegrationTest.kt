package com.vwdh.robot.domain.applicationservice.service

import com.vwdh.robot.domain.applicationservice.dto.RobotMoveResponse
import com.vwdh.robot.domain.core.exception.InvalidConstantValueException
import com.vwdh.robot.domain.core.exception.MoveNotWithinTheFloorLimitsException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RobotsMoveServiceIntegrationTest {

    @Autowired
    lateinit var robotsMoveService: RobotsMoveService

    companion object {
        @JvmStatic
        fun happyPathDataSource() = listOf(
            Arguments.of(
                """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent(),
                """ 
            1 3 N
            5 1 E
        """.trimIndent()
            ),
            Arguments.of(
                """
            2 2
            0 0 N
            MM
            0 0 N
            RM
        """.trimIndent(),
                """ 
            0 2 N
            1 0 E
        """.trimIndent()
            ),
        )
    }

    @Nested
    inner class EdgeCases {
        @Test
        fun it_generates_exception_given_move_is_not_within_limits() {
            val input = """
            5 5
            0 0 S
            MM 
        """.trimIndent()
            assertThrows<MoveNotWithinTheFloorLimitsException> {
                robotsMoveService.executeRobotMoves(input)
            }
        }

        @Test
        fun it_generates_exception_given_start_position_is_invalid() {
            val input = """
            1 1
            2 2 S
            MM 
        """.trimIndent()
            assertThrows<MoveNotWithinTheFloorLimitsException> {
                robotsMoveService.executeRobotMoves(input)
            }
        }

        @Test
        fun it_generates_exception_given_orientation_identifier_is_invalid() {
            val input = """
            5 5
            0 0 T
            MM 
        """.trimIndent()
            assertThrows<InvalidConstantValueException> {
                robotsMoveService.executeRobotMoves(input)
            }
        }

        @Test
        fun it_generates_exception_given_move_identifier_is_invalid() {
            val input = """
            5 5
            0 0 N
            TT
        """.trimIndent()
            assertThrows<InvalidConstantValueException> {
                robotsMoveService.executeRobotMoves(input)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("happyPathDataSource")
    fun it_moves_the_robots_to_the_expected_position(input: String, expected: String) {
        val response = robotsMoveService.executeRobotMoves(input)
        Assertions.assertEquals(expected, convertListOfPositionsIntoPlainString(response))
    }

    fun convertListOfPositionsIntoPlainString(list: List<RobotMoveResponse>): String {
        val sb = StringBuilder()
        list.forEach { sb.append(it).append("\n") }
        return sb.toString().trimIndent()
    }

}
