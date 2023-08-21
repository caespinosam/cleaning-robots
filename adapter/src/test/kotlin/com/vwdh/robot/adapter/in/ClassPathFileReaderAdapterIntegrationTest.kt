package com.vwdh.robot.adapter.`in`

import com.vwdh.robot.adapter.`in`.responseprocessor.RobotsResponseProcessor
import com.vwdh.robot.application.model.valueobject.Orientation
import com.vwdh.robot.application.service.port.`in`.RobotsMoveUseCase
import com.vwdh.robot.application.service.port.`in`.dto.RobotMoveResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ClassPathFileReaderAdapterIntegrationTest(
    @MockK
    private val robotsMoveUseCaseMock: RobotsMoveUseCase,
    @MockK
    private val robotsResponseProcessorMock: RobotsResponseProcessor
) {

    private val classPathFileReaderAdapter =
        ClassPathFileReaderAdapter(robotsMoveUseCaseMock, robotsResponseProcessorMock)

    @Test
    fun it_reads_text_from_file_in_classpath() {
        val mockResponse = listOf(RobotMoveResponse(0, 0, Orientation.E))
        every { robotsMoveUseCaseMock.executeRobotMoves(any()) } returns mockResponse
        every { robotsResponseProcessorMock.process(mockResponse) } just Runs

        classPathFileReaderAdapter.run()

        val slotInput = slot<String>()
        verify(exactly = 1) { robotsMoveUseCaseMock.executeRobotMoves(capture(slotInput)) }
        val textSent = slotInput.captured.trimIndent()
        Assertions.assertThat(textSent).isEqualTo(
            """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
            """.trimIndent()
        )
    }
}