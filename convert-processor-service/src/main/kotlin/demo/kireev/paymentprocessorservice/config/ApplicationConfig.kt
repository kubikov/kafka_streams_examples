package demo.kireev.paymentprocessorservice.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface LoggerTrait {
    fun logger(): Logger = LoggerFactory.getLogger(this::class.java)
}


fun objectMapper() = ObjectMapper().apply {
    registerModule(JavaTimeModule())
    registerModule(KotlinModule())
    propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
}

inline fun <reified T> convertTo(json: String?): T {
    try {
        val type = object : TypeReference<T>() {}
        return objectMapper().readValue(json, type) ?: throw IllegalArgumentException("Json string is empty")
    } catch (e: JsonProcessingException) {
        throw IllegalArgumentException("Invalid json string: ${e.message}")
    }
}
