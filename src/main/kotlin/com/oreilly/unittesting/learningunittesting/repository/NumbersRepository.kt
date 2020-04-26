package com.oreilly.unittesting.learningunittesting.repository

import org.springframework.stereotype.Repository

interface NumbersRepository {

    fun getNumbers(): List<Int>
}


@Repository
class NumberRepositoryImpl : NumbersRepository {

    override fun getNumbers(): List<Int> {
        TODO("Not yet implemented")
    }
}