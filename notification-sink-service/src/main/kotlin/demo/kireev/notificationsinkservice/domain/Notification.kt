package demo.kireev.notificationsinkservice.domain

import demo.kireev.notificationsinkservice.domain.enums.Currency
import demo.kireev.notificationsinkservice.domain.enums.Status
import java.math.BigDecimal
import java.util.UUID

data class Notification(
    val eventId: UUID,
    var status: Status,
    var currency: Currency?,
    var price: BigDecimal?,
    var paymentConfirm: Boolean?,
    val name: String?,
    val stockConfirm: Boolean?
)
