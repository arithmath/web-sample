package jp.arithmath.websample.util

import com.fasterxml.jackson.databind.ObjectMapper
import jp.arithmath.websample.extension.readValue

object JsonConverter {
    val objectMapper = ObjectMapper()

    inline fun <reified T> toObject(jsonString: String): T {
        return objectMapper.readValue(jsonString)
    }

    fun <T> toJsonString(obj: T): String {
        return objectMapper.writeValueAsString(obj)
    }
}