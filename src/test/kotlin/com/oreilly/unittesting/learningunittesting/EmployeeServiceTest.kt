package com.oreilly.unittesting.learningunittesting

import com.oreilly.unittesting.learningunittesting.model.Employee
import com.oreilly.unittesting.learningunittesting.repository.EmployeeRepository
import com.oreilly.unittesting.learningunittesting.service.EmployeeService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class EmployeeServiceTest {

    @InjectMocks
    private lateinit var employeeService: EmployeeService

    @Mock
    private lateinit var employeeRepository: EmployeeRepository

    @Test
    fun `getEmployee() basic test`() {
        val employee = Employee("Ahmed", "ahmed@gmail.com").apply { id = 1 }
        `when`(employeeRepository.getOne(anyInt())).thenReturn(employee)
        assertEquals(employee, employeeService.getEmployee(1))
    }

    @Test
    fun `getAllEmployees() basic test`() {
        val employees = mutableListOf(
                Employee("Dina", "dina@gmail.com").apply { id = 1 },
                Employee("Ahmed", "ahmed@gmail.com").apply { id = 2 },
                Employee("Khalifa", "khalifa@gmail.com").apply { id = 3 }
        )
        `when`(employeeRepository.findAll()).thenReturn(employees)
        assertEquals(employees, employeeService.getAllEmployees())
    }
}