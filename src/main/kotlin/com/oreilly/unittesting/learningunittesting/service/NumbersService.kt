package com.oreilly.unittesting.learningunittesting.service

import com.oreilly.unittesting.learningunittesting.repository.NumbersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NumbersService {

    @Autowired
    lateinit var numbersRepository: NumbersRepository

    fun getSum() = numbersRepository.getNumbers().sum()
}