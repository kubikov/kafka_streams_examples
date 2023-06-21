package demo.kireev.notificationsinkservice

import demo.kireev.notificationsinkservice.config.convertTo
import demo.kireev.notificationsinkservice.domain.Notification
import demo.kireev.notificationsinkservice.service.NotificationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    val service: NotificationService
) {

    @KafkaListener(topics = ["outbox.event.notification"])
    fun listener(payload: String) {
        val notification = convertTo<Notification>(payload)
        service.createOrUpdate(notification)
    }
}
