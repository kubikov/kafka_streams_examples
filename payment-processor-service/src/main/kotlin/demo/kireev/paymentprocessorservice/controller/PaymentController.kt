package demo.kireev.paymentprocessorservice.controller


import demo.kireev.paymentprocessorservice.service.PaymentService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
class PaymentController(
    val service: PaymentService
) {

    @GetMapping("/confirm/{id}")
    fun confirm(@PathVariable id: UUID): ResponseEntity<Any>  {
        service.confirm(id)
        return ResponseEntity.ok().build()
    }

}
