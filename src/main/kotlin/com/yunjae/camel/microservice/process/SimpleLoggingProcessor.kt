package com.yunjae.camel.microservice.process

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.slf4j.LoggerFactory

class SimpleLoggingProcessor: Processor {
    val logger = LoggerFactory.getLogger(SimpleLoggingProcessor::class.java)

    override fun process(exchange: Exchange?) {
        logger.info("SimpleLoggingProcessor {}", exchange?.message?.body)
    }
}