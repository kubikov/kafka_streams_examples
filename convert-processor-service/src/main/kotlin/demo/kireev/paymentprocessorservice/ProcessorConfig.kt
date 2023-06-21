package demo.kireev.paymentprocessorservice

import demo.kireev.paymentprocessorservice.config.LoggerTrait
import demo.kireev.paymentprocessorservice.config.convertTo
import demo.kireev.paymentprocessorservice.config.objectMapper
import demo.kireev.paymentprocessorservice.domain.Payment
import demo.kireev.paymentprocessorservice.service.ConvertService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function

@Configuration
class ProcessorConfig(val service: ConvertService) : LoggerTrait {
    @Bean
    fun process(): Function<String, String> = Function { payload ->
        logger().info("Message $payload")

        val payment = convertTo<Payment>(payload)

        objectMapper().writeValueAsString(
            service.convert(payment)
        )
    }

}
