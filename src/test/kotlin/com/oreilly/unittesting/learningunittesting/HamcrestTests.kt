package com.oreilly.unittesting.learningunittesting

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class HamcrestTests {

    @Test
    fun `test assertions with hamcrest`() {
        val list = listOf(1, 2, 3, 4)
        assertThat(list, hasSize(4))
        assertThat(list, hasItems(2, 4))
        assertThat(list, everyItem(greaterThan(0)))
        assertThat(list, everyItem(lessThan(100)))

        assertThat("", `is`(emptyString()))
        assertThat("ABC", startsWith("AB"))
        assertThat("CDE", endsWith("E"))
    }
}