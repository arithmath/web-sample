package jp.arithmath.websample.websocket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler

@Component
class BinaryHandler: BinaryWebSocketHandler() {
    val sessions = mutableMapOf<String, WebSocketSession>()
    val logger = LoggerFactory.getLogger(javaClass)

    override fun afterConnectionEstablished(session: WebSocketSession): Unit {
        logger.info("afterConnectionEstablished")
        val id = session.id
        logger.info("id = {}", id)
        sessions.put(id, session)
    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage): Unit {
        logger.info("handleBinaryMessage")
        val id = session?.id
        logger.info("id = {}", id)
        logger.info("message = {}", message)
        logger.info("message.toString = {}", message.toString())

        sessions.forEach {
            val id = it.key
            val session = it.value
            session.sendMessage(message)
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("afterConnectionClosed")
        val id = session.id
        logger.info("id = {}", id)
        sessions.remove(id);
    }
}