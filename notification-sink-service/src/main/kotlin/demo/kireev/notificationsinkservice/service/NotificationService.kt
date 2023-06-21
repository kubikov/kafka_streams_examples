package demo.kireev.notificationsinkservice.service

import demo.kireev.notificationsinkservice.domain.Notification
import demo.kireev.notificationsinkservice.domain.enums.Status
import demo.kireev.notificationsinkservice.repository.NotificationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationService(
    val repository: NotificationRepository
) {

    fun getNotification(id: UUID) = repository.getNotification(id)

    fun createOrUpdate(notification: Notification) {
        // Check if Event from Payment-service
        // - enrich and set status READY
        // - or create
        if (notification.paymentConfirm != null) {

            /*val notificationToUpdate = repository.getNotification(notification.eventId)

            notificationToUpdate.currency = notification.currency
            notificationToUpdate.paymentConfirm = notification.paymentConfirm
            notificationToUpdate.price = notification.price
            notificationToUpdate.status = Status.READY*/

            repository.update(notification)
        } else {
            repository.create(notification)
        }
    }

}
