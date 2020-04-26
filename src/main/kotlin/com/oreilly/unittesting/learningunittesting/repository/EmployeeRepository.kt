package com.oreilly.unittesting.learningunittesting.repository

import com.oreilly.unittesting.learningunittesting.model.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Int>