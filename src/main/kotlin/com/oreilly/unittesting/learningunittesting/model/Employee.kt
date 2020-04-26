package com.oreilly.unittesting.learningunittesting.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Employee(val name: String? = "", val email: String? = "") {

    @Suppress("unused")
    @Id
    var id: Int? = null
}