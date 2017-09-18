package jp.arithmath.websample.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import jp.arithmath.websample.dto.request.TestApiRequest
import jp.arithmath.websample.dto.response.TestApiResponse
import jp.arithmath.websample.dto.response.WebSocketResponse
import jp.arithmath.websample.util.JsonConverter
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.net.URI

@Component
class MessageHandler(): TextWebSocketHandler() {
    val sessions = mutableMapOf<String, WebSocketSession>()
    val logger = LoggerFactory.getLogger(javaClass)
    val restTemplate = RestTemplate()

    override fun afterConnectionEstablished(session: WebSocketSession): Unit {
        logger.info("afterConnectionEstablished")
        val id = session.id
        logger.info("id = {}", id)
        sessions.put(id, session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage): Unit {
        logger.info("handleTextMessage")
        val id = session?.id
        logger.info("id = {}", id)
        logger.info("message = {}", message)
        logger.info("message.toString = {}", message.toString())
        logger.info("message.payload = {}", message.payload)

        val response = requestApi2(message.payload)

        sessions.forEach {
            val id = it.key
            val session = it.value
            //session.sendMessage(TextMessage(response.resultCode + ":" + response.message))
            val webSocketResponse = WebSocketResponse("0000", "success", response)
            val resultMessage = JsonConverter.toJsonString(webSocketResponse)
            session.sendMessage(TextMessage(resultMessage))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info("afterConnectionClosed")
        val id = session.id
        logger.info("id = {}", id)
        sessions.remove(id);
    }

    fun requestApi1(requestBodyString: String): TestApiResponse {
        val mapper = ObjectMapper();
        val testApiRequest = mapper.readValue(requestBodyString, TestApiRequest::class.java);
        val response = restTemplate.postForObject("http://localhost:8080/api/test", testApiRequest, TestApiResponse::class.java)
        return response
    }

    fun requestApi2(requestBodyString: String): TestApiResponse {
        val request = RequestEntity.post(URI("http://localhost:8080/api/test"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(requestBodyString)
        val response = restTemplate.exchange(request, TestApiResponse::class.java)
        return response.body
    }
}