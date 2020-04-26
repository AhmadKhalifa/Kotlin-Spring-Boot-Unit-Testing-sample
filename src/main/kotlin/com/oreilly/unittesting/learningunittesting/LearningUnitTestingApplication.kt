package com.oreilly.unittesting.learningunittesting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LearningUnitTestingApplication

fun main(args: Array<String>) {
    runApplication<LearningUnitTestingApplication>(*args)
}
