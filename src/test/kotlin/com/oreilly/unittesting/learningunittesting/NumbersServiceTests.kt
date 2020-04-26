package com.oreilly.unittesting.learningunittesting

import com.oreilly.unittesting.learningunittesting.repository.NumbersRepository
import com.oreilly.unittesting.learningunittesting.service.NumbersService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class NumbersServiceTests {

    @InjectMocks
    private val numbersService = NumbersService()

    @Mock
    private lateinit var numberRepository: NumbersRepository

//    @BeforeEach
//    fun beforeTests() {
//        numbersService.numbersRepository = numberRepository
//    }

    @Test
    fun `numbersService#getSum() test (basic)`() {
        `when`(numberRepository.getNumbers()).thenReturn(listOf(1, 2, 3))
        assertEquals(6, numbersService.getSum())
    }

    @Test
    fun `numbersService#getSum() test (one value)`() {
        `when`(numberRepository.getNumbers()).thenReturn(listOf())
        assertEquals(0, numbersService.getSum())
    }

    @Test
    fun `numbersService#getSum() test (no values)`() {
        `when`(numberRepository.getNumbers()).thenReturn(listOf(5))
        assertEquals(5, numbersService.getSum())
    }
}