package com.oreilly.unittesting.learningunittesting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class ListTests {

    @Test
    fun `List#size() test`() {
        val listMock = mock(List::class.java)
        `when`(listMock.size).thenReturn(5).thenReturn(10)
        assertEquals(5, listMock.size)
        assertEquals(10, listMock.size)
    }

    @Test
    fun `List#get() test with parameter`() {
        val listMock = mock(List::class.java)
        `when`(listMock[0]).thenReturn("00")
        `when`(listMock[1]).thenReturn("11")
        assertEquals(listMock[0], "00")
        assertEquals(listMock[1], "11")
    }

    @Test
    fun `List#get() test with any parameter`() {
        val listMock = mock(List::class.java)
        `when`(listMock[anyInt()]).thenReturn("index")
        assertEquals(listMock[1], "index")
        assertEquals(listMock[2], "index")
        assertEquals(listMock[3], "index")
    }

    @Test
    fun `List#get() call verification`() {
        val listMock = mock(List::class.java)
        listMock[0]
        listMock[1]
        verify(listMock)[0]
        verify(listMock)[1]
        verify(listMock, atLeastOnce())[anyInt()]
        verify(listMock, atMost(2))[anyInt()]
        verify(listMock, times(2))[anyInt()]
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `List#get() capture argument value test`() {
        val listMock = mock(ArrayList::class.java) as ArrayList<String>
        listMock.add("Hello")
        val captor = ArgumentCaptor.forClass(String::class.java)
        verify(listMock).add(captor.capture())
        assertEquals("Hello", captor.value)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `List#get() capture multiple argument value test`() {
        val listMock = mock(ArrayList::class.java) as ArrayList<String>
        listMock.add("Hello")
        listMock.add("World")
        val captor = ArgumentCaptor.forClass(String::class.java)
        verify(listMock, times(2)).add(captor.capture())
        assertEquals("Hello", captor.allValues[0])
        assertEquals("World", captor.allValues[1])
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `List spy test`() {
        val listSpy = spy(ArrayList::class.java) as ArrayList<String>
        listSpy.add("Hi")
        assertEquals(1, listSpy.size)
        listSpy.add("There")
        assertEquals(2, listSpy.size)
        `when`(listSpy.size).thenReturn(10)
        listSpy.add("Hello")
        assertEquals(10, listSpy.size)
        verify(listSpy, times(3)).add(anyString())
        verify(listSpy).add("Hi")
    }
}