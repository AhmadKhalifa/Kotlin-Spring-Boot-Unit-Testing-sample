package com.oreilly.unittesting.learningunittesting.service

import com.oreilly.unittesting.learningunittesting.model.Employee
import com.oreilly.unittesting.learningunittesting.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun getEmployee(id: Int) = employeeRepository.getOne(id)

    fun getAllEmployees(): MutableList<Employee> = employeeRepository.findAll()

    fun saveEmployee(employee: Employee) = employeeRepository.save(employee)
}