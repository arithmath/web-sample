package jp.arithmath.websample.extension

import com.fasterxml.jackson.databind.ObjectMapper

inline fun <reified T> ObjectMapper.readValue(src: String): T {
    return readValue(src, T::class.java)
}