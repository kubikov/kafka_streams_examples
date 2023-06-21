package demo.kireev.paymentprocessorservice.service

import demo.kireev.paymentprocessorservice.domain.Payment
import demo.kireev.paymentprocessorservice.domain.enums.Currency
import org.springframework.stereotype.Service


@Service
class ConvertService() {

    fun convert(payment: Payment): Payment {
        payment.price = payment.price * 25000.00.toBigDecimal()
        payment.currency = Currency.USD
        return payment
    }

}
