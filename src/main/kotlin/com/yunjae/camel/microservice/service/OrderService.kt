package com.yunjae.camel.microservice.service

import com.yunjae.camel.microservice.model.Order
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class OrderService {

    lateinit var list: MutableList<Order>

    @PostConstruct
    fun initDB() {
        list = mutableListOf(
            Order(67, "Mobile", 5000.0),
            Order(89, "Book", 400.0),
            Order(45, "AC", 1500.0),
            Order(67, "Shoes", 4000.0),
        )
    }

    fun addOrder(order: Order): MutableList<Order> {
        list.add(order)
        return list;
    }

    fun getOrders(): MutableList<Order> {
        return list;
    }
}