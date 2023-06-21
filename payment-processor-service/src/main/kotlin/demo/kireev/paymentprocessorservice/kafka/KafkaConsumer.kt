package demo.kireev.paymentprocessorservice.kafka

import demo.kireev.paymentprocessorservice.config.convertTo
import demo.kireev.paymentprocessorservice.domain.Payment
import demo.kireev.paymentprocessorservice.service.PaymentService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    val service: PaymentService
) {

    @KafkaListener(topics = ["outbox.event.payment"])
    fun listener(payload: String) {
        val payment = convertTo<Payment>(payload)
        service.create(payment)

    }
}