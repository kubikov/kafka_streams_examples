package demo.kireev.notificationsinkservice.controller

import demo.kireev.notificationsinkservice.domain.Notification
import demo.kireev.notificationsinkservice.service.NotificationService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
class NotificationController(
    val service: NotificationService
) {

    @GetMapping("/notification/{id}")
    fun getNotification(@PathVariable id: UUID): Notification {
        return service.getNotification(id)

    }
}
