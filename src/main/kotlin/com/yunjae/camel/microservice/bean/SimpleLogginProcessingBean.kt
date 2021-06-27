package com.yunjae.camel.microservice.bean

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SimpleLogginProcessingBean {

    val logger = LoggerFactory.getLogger(SimpleLogginProcessingBean::class.java)

    fun process(message: String) {
        logger.info("SimpleLogginProcessBean {}", message)
    }
}