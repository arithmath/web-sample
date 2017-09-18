package jp.arithmath.websample.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

abstract class AbstractJsonWebSocketHandler<T>(val objectMapper: ObjectMapper): TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage): Unit {
        val result = handleJsonMessage(session, message)
        objectMapper.writeValueAsString(result)
    }

    abstract fun handleJsonMessage(session: WebSocketSession, message: TextMessage): T
}