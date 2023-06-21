package demo.kireev.paymentprocessorservice.repository


import demo.kireev.paymentprocessorservice.domain.Payment
import demo.kireev.paymentprocessorservice.domain.enums.Currency
import demo.kireev.paymentprocessorservice.jooq.Tables.*

import org.jooq.DSLContext
import org.jooq.JSONB
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PaymentRepository(val dslContext: DSLContext) {


    private val p = PAYMENT
    private val o = OUTBOX_EVENT

    fun create(payment: Payment) {
        dslContext.dsl().insertInto(p)
            .set(p.ID, payment.eventId)
            .set(p.CURRENCY, payment.currency.toString())
            .set(p.PRICE, payment.price)
            .set(p.CONFIRM, payment.confirm)
            .execute()
    }

    fun confirm(id: UUID) {
        dslContext.dsl().update(p)
            .set(p.CONFIRM, true)
            .where(p.ID.eq(id))
            .execute()
    }

    fun getPayment(id: UUID) = dslContext.dsl().select(p.ID,p.CURRENCY,p.PRICE,p.CONFIRM)
        .from(p)
            .where(p.ID.eq(id))
        .limit(1)
            .map { r ->
                Payment(
                    eventId = UUID.fromString(r[p.ID].toString()),
                    currency = Currency.valueOf(r[p.CURRENCY]),
                    price = r[p.PRICE],
                    confirm = r[p.CONFIRM]
                )

            }.first()

}
