package jp.arithmath.websample.config

import jp.arithmath.websample.websocket.BinaryHandler
import jp.arithmath.websample.websocket.MessageHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
        val messageHandler: MessageHandler,
        val binaryHandler: BinaryHandler): WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(messageHandler, "/ws/test")
        registry.addHandler(binaryHandler, "/ws/binaryTest")
    }
}