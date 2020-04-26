package com.oreilly.unittesting.learningunittesting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class AssertJTests {

    @Test
    fun `test assertions with assertJ`() {
        val list = listOf(1, 2, 3, 4)
//        MatcherAssert.assertThat(list, Matchers.hasSize(4))
        assertThat(list).hasSize(4)
//        MatcherAssert.assertThat(list, Matchers.hasItems(2, 4))
        assertThat(list).contains(2, 4)

//        MatcherAssert.assertThat(list, Matchers.everyItem(Matchers.greaterThan(0)))
        assertThat(list).allMatch { it > 0 }

//        MatcherAssert.assertThat(list, Matchers.everyItem(Matchers.lessThan(100)))
        assertThat(list).allMatch { it < 100 }


        assertThat(list).hasSize(4).contains(2, 4).allMatch { it > 0 }.allMatch { it < 100 }
//
//        MatcherAssert.assertThat("", Matchers.`is`(Matchers.emptyString()))
        assertThat("").isEmpty()

//        MatcherAssert.assertThat("ABC", Matchers.startsWith("AB"))
        assertThat("ABC").startsWith("AB")

//        MatcherAssert.assertThat("CDE", Matchers.endsWith("E"))
        assertThat("CDE").endsWith("E")
    }
}