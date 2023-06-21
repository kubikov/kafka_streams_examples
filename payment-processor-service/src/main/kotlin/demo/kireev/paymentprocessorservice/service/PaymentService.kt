package demo.kireev.paymentprocessorservice.service

import demo.kireev.paymentprocessorservice.config.objectMapper
import demo.kireev.paymentprocessorservice.domain.Notification
import demo.kireev.paymentprocessorservice.domain.Payment
import demo.kireev.paymentprocessorservice.domain.enums.Status
import demo.kireev.paymentprocessorservice.kafka.KafkaProducer
import demo.kireev.paymentprocessorservice.repository.PaymentRepository
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Service
import java.util.*


@Service
class PaymentService(
    val repository: PaymentRepository,
    val kafkaProducer: KafkaProducer
) {




    fun confirm(id: UUID) {
        repository.confirm(id)
        val payment = repository.getPayment(id)
        val notification = Notification(
            eventId = payment.eventId,
            status = Status.READY,
            currency = payment.currency,
            price = payment.price,
            paymentConfirm = payment.confirm,
            name = null,
            stockConfirm = false

        )
        kafkaProducer.sendMessage(notification)

    }



    fun create(payment: Payment) {
        repository.create(payment)
    }
}
