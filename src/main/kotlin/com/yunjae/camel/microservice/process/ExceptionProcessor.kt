package com.yunjae.camel.microservice.process

import com.yunjae.camel.microservice.exception.CamelCustomException
import org.apache.camel.Exchange
import org.apache.camel.Processor

class ExceptionProcessor: Processor {
    override fun process(exchange: Exchange?) {
        println("Exception Throw")
        throw CamelCustomException("CamelCustomException");
    }
}