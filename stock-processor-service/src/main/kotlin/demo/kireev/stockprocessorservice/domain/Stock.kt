package demo.kireev.stockprocessorservice.domain

import java.util.UUID

data class Stock(
    val eventId: UUID,
    val name: String,
    var confirm: Boolean
)
