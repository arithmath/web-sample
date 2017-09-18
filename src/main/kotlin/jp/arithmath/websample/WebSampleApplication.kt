package jp.arithmath.websample

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WebSampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebSampleApplication::class.java, *args)
}

@Bean
fun objectMapper(): ObjectMapper {
    return ObjectMapper()
}