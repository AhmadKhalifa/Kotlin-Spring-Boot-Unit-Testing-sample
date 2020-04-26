package com.oreilly.unittesting.learningunittesting

import com.oreilly.unittesting.learningunittesting.model.Employee
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestPropertySource(locations = ["classpath:integration-test.properties"])
class EmployeesIntegrationTest {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `getAllEmployees() basic test`() {
        val response = restTemplate.getForObject("/employees/all", String::class.java)
        JSONAssert.assertEquals("[{id: 1}, {id: 2}]", response, false)
    }

    @Test
    fun `getEmployee() basic test`() {
        val employee = restTemplate.getForObject("/employees/1", Employee::class.java)
        assertEquals("Ahmed", employee.name)
    }
}