package com.oreilly.unittesting.learningunittesting

import com.oreilly.unittesting.learningunittesting.controller.EmployeeController
import com.oreilly.unittesting.learningunittesting.model.Employee
import com.oreilly.unittesting.learningunittesting.service.EmployeeService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(EmployeeController::class)
class EmployeeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var employeeService: EmployeeService

    @Test
    fun `showHelloWorld() basic test`() {
        val requestBuilder = MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON)
        val result = mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk)
                .andExpect(content().string("Employees controller"))
                .andReturn()
        assertEquals("Employees controller", result.response.contentAsString)
    }

    @Test
    fun `getDummyEmployee() basic test`() {
        val requestBuilder = MockMvcRequestBuilders
                .get("/employees/dummy")
                .accept(MediaType.APPLICATION_JSON)
        val result = mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk)
                .andReturn()
        JSONAssert.assertEquals(
                "{ id: 1, name: Ahmed, email: Ahmed@gmail.com }",
                result.response.contentAsString,
                true
        )
    }

    @Test
    fun `getEmployee() basic test`() {
        `when`(employeeService.getEmployee(anyInt()))
                .thenReturn(Employee("Dina", "dina@gmail.com").apply { id = 1 })
        val requestBuilder = MockMvcRequestBuilders
                .get("/employees/2")
                .accept(MediaType.APPLICATION_JSON)
        val result = mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk)
                .andReturn()
        JSONAssert.assertEquals(
                "{ id: 1, name: Dina, email: dina@gmail.com }",
                result.response.contentAsString,
                true
        )
    }

    @Test
    fun `getAllEmployees() basic test`() {
        `when`(employeeService.getAllEmployees()).thenReturn(mutableListOf(
                Employee("Dina", "dina@gmail.com").apply { id = 1 },
                Employee("Ahmed", "ahmed@gmail.com").apply { id = 2 },
                Employee("Khalifa", "khalifa@gmail.com").apply { id = 3 }
        ))
        val result = mockMvc
                .perform { servletContext ->
                    MockMvcRequestBuilders
                            .get("/employees/all")
                            .accept(MediaType.APPLICATION_JSON)
                            .buildRequest(servletContext)
                }
                .andExpect(status().isOk)
                .andReturn()
        JSONAssert.assertEquals(
                "[" +
                        "{ id: 1, name: Dina, email: dina@gmail.com }," +
                        "{ id: 2, name: Ahmed, email: ahmed@gmail.com }," +
                        "{ id: 3, name: Khalifa, email: khalifa@gmail.com }" +
                        "]",
                result.response.contentAsString,
                false
        )
    }

    @Test
    fun `saveEmployee() basic test`() {
        val employee = Employee("Ahmed", "ahmed@gmail.com").apply { id = 1 }
        `when`(employeeService.saveEmployee(employee)).thenReturn(employee)
        val result = mockMvc
                .perform { servletContext ->
                    MockMvcRequestBuilders
                            .post("/employees")
                            .accept(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Ahmed\",\"email\":\"ahmed@gmail.com\",\"id\":1}")
                            .contentType(MediaType.APPLICATION_JSON)
                            .buildRequest(servletContext)
                }
                .andExpect(status().is2xxSuccessful)
                .andReturn()
        JSONAssert.assertEquals(
                "{\"name\":\"Ahmed\",\"email\":\"ahmed@gmail.com\",\"id\":1}",
                result.response.contentAsString,
                true
        )
    }
}