package demo.kireev.notificationsinkservice.repository

import demo.kireev.notificationsinkservice.domain.Notification
import demo.kireev.notificationsinkservice.domain.enums.Currency
import demo.kireev.notificationsinkservice.domain.enums.Status
import demo.kireev.notificationsinkservice.jooq.Tables.NOTIFICATION
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class NotificationRepository(val dslContext: DSLContext) {

    private val n = NOTIFICATION

    fun create(notification: Notification) {
        dslContext.dsl().insertInto(n)
            .set(n.ID, notification.eventId)
            .set(n.STATUS, notification.status.toString())
            .set(n.NAME, notification.name)
            .set(n.CURRENCY, notification.currency.toString())
            .set(n.PRICE, notification.price)
            .set(n.PAYMENT_CONFIRM, notification.paymentConfirm)
            .set(n.STOCK_CONFIRM, notification.stockConfirm)
            .execute()
    }

    fun getNotification(id: UUID): Notification = dslContext.dsl().select(
        n.ID, n.STATUS, n.NAME, n.CURRENCY, n.PRICE, n.PAYMENT_CONFIRM, n.STOCK_CONFIRM
    )
        .from(n)
        .where(n.ID.eq(id))
        .limit(1)
        .map { r ->
            Notification(
                eventId = UUID.fromString(r[n.ID].toString()),
                status = Status.valueOf(r[n.STATUS]),
                name = r[n.NAME],
                currency = Currency.valueOf(r[n.CURRENCY]),
                price = r[n.PRICE],
                paymentConfirm = r[n.PAYMENT_CONFIRM],
                stockConfirm = r[n.STOCK_CONFIRM]
            )
        }.first()

    fun update(notification: Notification) {
        dslContext.dsl().update(n)
            .set(n.STATUS, Status.READY.toString())
            .where(n.ID.eq(notification.eventId))
            .execute()
    }
}
