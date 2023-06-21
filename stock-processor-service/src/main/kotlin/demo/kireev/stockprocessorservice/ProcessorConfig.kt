package demo.kireev.stockprocessorservice

import demo.kireev.stockprocessorservice.domain.Notification
import demo.kireev.stockprocessorservice.domain.Stock
import demo.kireev.stockprocessorservice.domain.enums.Status
import demo.kireev.stockprocessorservice.service.StockService
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function

private val logger = KotlinLogging.logger {}

@Configuration
class ProcessorConfig(val service: StockService) {
    @Bean
    fun process(): Function<String, String> = Function { payload ->

        // получает
        logger.info { "Message $payload" }

        val stock = convertTo<Stock>(payload)
        val confirmedStock = service.confirm(stock)

        val notification = Notification(
            eventId = confirmedStock.eventId,
            status = Status.CREATED,
            currency = null,
            price = null,
            paymentConfirm = null,
            name = stock.name,
            stockConfirm = confirmedStock.confirm
        )

        // отправялет
        objectMapper().writeValueAsString(notification)
    }
}



