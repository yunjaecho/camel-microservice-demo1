package com.yunjae.camel.microservice.bean

import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class CurrentTimeBean {
    fun time(): String {
        return "Time now is ${LocalTime.now()}"
    }
}