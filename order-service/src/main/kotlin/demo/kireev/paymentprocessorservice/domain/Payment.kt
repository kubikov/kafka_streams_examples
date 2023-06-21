package demo.kireev.paymentprocessorservice.domain

import demo.kireev.paymentprocessorservice.domain.enums.Currency
import java.math.BigDecimal
import java.util.UUID

data class Payment(
    val eventId: UUID,
    val currency: Currency,
    val price: BigDecimal,
    val confirm: Boolean
)
