package jp.arithmath.websample.util

import com.fasterxml.jackson.databind.ObjectMapper
import jp.arithmath.websample.extension.readValue
import org.junit.Assert
import org.junit.Test

class JsonConverterTest {
    @Test
    fun testToObject() {
        val result: Map<String, String> = JsonConverter.toObject("{\"key1\": \"value1\", \"key2\": \"value2\"}")
        System.out.println(result.toString())
        Assert.assertEquals("value1", result.get("key1"))
        Assert.assertEquals("value2", result.get("key2"))
    }

    @Test
    fun testToObject2() {
        val result: TestDto = JsonConverter.toObject("{\"id\": \"id001\", \"age\": 22, \"name\": \"alice\"}")
        System.out.println(result.toString())
        Assert.assertEquals("id001", result.id)
        Assert.assertEquals(22, result.age)
        Assert.assertEquals("alice", result.name)
    }

    @Test
    fun testToObject3() {
        val objectMapper = ObjectMapper()
        val result: TestDto = objectMapper.readValue("{\"id\": \"id001\", \"age\": 22, \"name\": \"alice\"}")
        System.out.println(result.toString())
        Assert.assertEquals("id001", result.id)
        Assert.assertEquals(22, result.age)
        Assert.assertEquals("alice", result.name)
        Assert.assertEquals("alice2", result.name)

    }

    class TestDto(var id: String = "", var age: Int? = null, var name: String = "")
}