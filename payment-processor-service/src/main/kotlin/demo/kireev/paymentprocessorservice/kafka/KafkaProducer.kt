package demo.kireev.paymentprocessorservice.kafka

import demo.kireev.paymentprocessorservice.config.objectMapper
import demo.kireev.paymentprocessorservice.domain.Notification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    @Value("\${kafka.topics.out-topic}") val topic: String,
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    fun sendMessage(notification: Notification) {
        val message: Message<String> = MessageBuilder
            .withPayload(objectMapper().writeValueAsString(notification))
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()
        kafkaTemplate.send(message)
    }
}