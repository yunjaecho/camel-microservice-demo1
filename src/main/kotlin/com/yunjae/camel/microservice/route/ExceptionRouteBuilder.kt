package com.yunjae.camel.microservice.route

import com.yunjae.camel.microservice.exception.CamelCustomException
import com.yunjae.camel.microservice.process.ExceptionProcessor
import org.apache.camel.CamelContext
import org.apache.camel.Processor
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

//@Component
class ExceptionRouteBuilder(context: CamelContext?): RouteBuilder(context) {
    override fun configure() {
        onException(CamelCustomException::class.java).process {
            println("handling ex")
        }.log("Received by \${body}").handled


        from("file:files/input2").process(ExceptionProcessor()).to("file:files/output")



    }
}