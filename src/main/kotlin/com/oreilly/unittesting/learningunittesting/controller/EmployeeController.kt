package com.oreilly.unittesting.learningunittesting.controller

import com.oreilly.unittesting.learningunittesting.model.Employee
import com.oreilly.unittesting.learningunittesting.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var employeeService: EmployeeService

    @GetMapping
    fun showHelloWorld() = "Employees controller"

    @GetMapping("/dummy")
    fun getDummyEmployee() = Employee("Ahmed", "Ahmed@gmail.com").apply { id = 1 }

    @GetMapping("/{id}")
    fun getEmployee(@PathVariable("id") id: Int) = employeeService.getEmployee(id)

    @GetMapping("/all")
    fun getAllEmployees() = employeeService.getAllEmployees()

    @PostMapping
    fun saveEmployee(@RequestBody employee: Employee) = employeeService.saveEmployee(employee)
}