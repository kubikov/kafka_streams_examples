package demo.kireev.notificationsinkservice.domain

import demo.kireev.notificationsinkservice.domain.enums.Currency
import java.math.BigDecimal
import java.util.UUID

data class Payment(
    val eventId: UUID,
    val currency: Currency,
    val price: BigDecimal,
    val confirm: Boolean
)
