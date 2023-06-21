package demo.kireev.stockprocessorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockProcessorServiceApplication

fun main(args: Array<String>) {
	runApplication<StockProcessorServiceApplication>(*args)
}
