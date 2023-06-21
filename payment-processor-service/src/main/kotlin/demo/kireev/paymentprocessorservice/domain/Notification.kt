package demo.kireev.paymentprocessorservice.domain

import demo.kireev.paymentprocessorservice.domain.enums.Currency
import demo.kireev.paymentprocessorservice.domain.enums.Status
import java.math.BigDecimal
import java.util.UUID

data class Notification(
    val eventId: UUID,
    val status: Status,
    val currency: Currency,
    val price: BigDecimal,
    val paymentConfirm: Boolean,
    val name: String?,
    val stockConfirm: Boolean
)
