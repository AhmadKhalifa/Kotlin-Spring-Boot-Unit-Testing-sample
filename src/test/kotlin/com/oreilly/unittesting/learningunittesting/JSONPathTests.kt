package com.oreilly.unittesting.learningunittesting

import com.jayway.jsonpath.JsonPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class JSONPathTests {

    @Test
    fun `test assertions with JSONPath`() {
        val response = "[{\"name\":\"Ahmed\",\"email\":\"ahmed@gmail.com\",\"id\":1},{\"name\":\"Khalifa\",\"email\":\"khalifa@gmail.com\",\"id\":2}]"
        val documentContext = JsonPath.parse(response)
        assertThat(documentContext.read("$.length()") as Int).isEqualTo(2)
        assertThat(documentContext.read("$..id") as List<Int>).containsExactly(1, 2)
        println((documentContext.read("$.[1]") as Any).toString())
        println((documentContext.read("$.[0:1]") as Any).toString())
        println((documentContext.read("$.[?(@.name=='Ahmed')]") as Any).toString())
        println((documentContext.read("$.[?(@.id=='2')]") as Any).toString())
    }
}