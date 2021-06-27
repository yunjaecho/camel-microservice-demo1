package com.yunjae.camel.microservice.process

import com.yunjae.camel.microservice.model.Order
import com.yunjae.camel.microservice.service.OrderService
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.springframework.stereotype.Component

@Component
class OrderProcessor(val service: OrderService): Processor {

    override fun process(exchange: Exchange?) {
        exchange?.let {
            service.addOrder(exchange.getIn().getBody(Order::class.java))
        }

    }
}