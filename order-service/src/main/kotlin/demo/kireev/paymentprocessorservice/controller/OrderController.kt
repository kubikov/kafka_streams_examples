package demo.kireev.paymentprocessorservice.controller

import demo.kireev.paymentprocessorservice.repository.OrderRepository
import org.jooq.JSONB
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
class OrderController(
    val repository: OrderRepository,
) {

    @GetMapping("/events")
    fun generateNotificationEvents(): ResponseEntity<Any> {
        repository.createNotificationEvent()
        return ResponseEntity.ok().build()

    }

    @GetMapping("/order/create/{product}")
    fun create(@PathVariable product: String): ResponseEntity<Any> {

        val eventId = UUID.randomUUID()

        // create outbox_event with Payment-payload
        val payment = """
            {
                "event_id": "$eventId", 
                "currency": "BITCOIN",
                "price": ${(0..10).random()},
                "confirm": false
            }
        """.trimIndent()
        repository.create("convert", payment)

        // create outbox_event with Stock-payload
        val stock = """
            {
                "event_id": "$eventId", 
                "name": "$product",
                "price": ${(0..10).random()},
                "confirm": false
            }
        """.trimIndent()
        repository.create("stock", stock)
        return ResponseEntity.ok().build()
    }
}
