package demo.kireev.stockprocessorservice.service

import demo.kireev.stockprocessorservice.domain.Stock
import org.springframework.stereotype.Service

@Service
class StockService {

    fun confirm(stock: Stock): Stock {
        stock.confirm = true
        return stock
    }
}