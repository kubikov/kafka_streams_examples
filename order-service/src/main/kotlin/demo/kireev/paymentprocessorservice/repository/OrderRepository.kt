package demo.kireev.paymentprocessorservice.repository

import demo.kireev.paymentprocessorservice.jooq.Tables.*
import org.jooq.*
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class OrderRepository(val dslContext: DSLContext) {

    private val o = OUTBOX_EVENT

    fun create(aggregationType: String, payload: String) {
        dslContext.dsl().insertInto(o)
            .set(o.ID, UUID.randomUUID())
            .set(o.AGGREGATETYPE, aggregationType)
            .set(o.AGGREGATEID, "1")
            .set(o.TYPE, "new")
            .set(o.PAYLOAD, JSONB.valueOf(payload))
            .execute()
    }

    fun createNotificationEvent() {

        for (i in 0..INSERT_COUNT) {

            val inserts: MutableList<InsertValuesStep5<Record, UUID, String, String, String, JSONB>> = mutableListOf()

            for (r in i * BATCH_SIZE + 1..i * BATCH_SIZE + BATCH_SIZE) {

                inserts.add(
                    dslContext.dsl()
                        .insertInto(o, o.ID, o.AGGREGATETYPE, o.AGGREGATEID, o.TYPE, o.PAYLOAD)
                        .values(
                            UUID.randomUUID(),
                            "notification",
                            "1",
                            "new",
                            JSONB.valueOf("{\"event_id\": \"${UUID.randomUUID()}\", \"currency\": \"BITCOIN\",\"price\": ${(0..10).random()},\"confirm\": false}")
                        )
                )
            }

            dslContext.dsl().batch(
                inserts
            ).execute()
        }
    }

    companion object {
        const val BATCH_SIZE = 5000
        const val INSERT_COUNT = 3
    }
}
