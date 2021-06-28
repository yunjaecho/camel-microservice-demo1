package com.yunjae.camel.microservice.route

import com.yunjae.camel.microservice.bean.CurrentTimeBean
import com.yunjae.camel.microservice.bean.SimpleLogginProcessingBean
import com.yunjae.camel.microservice.model.Order
import com.yunjae.camel.microservice.process.OrderProcessor
import com.yunjae.camel.microservice.service.OrderService
import org.apache.camel.BeanInject
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.rest.RestBindingMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

//@Component
class RestRouteBuilder(context: CamelContext?, val currentTimeBean: CurrentTimeBean) : RouteBuilder(context) {

    @Autowired
    lateinit var logginComponent: SimpleLogginProcessingBean

    @Autowired
    lateinit var service: OrderService

    @BeanInject
    lateinit var processor: OrderProcessor

    override fun configure() {
        restConfiguration()
            .component("servlet")
            .port(8080)
            .host("localhost")
            .bindingMode(RestBindingMode.json);

        rest()
            .get("/hello")
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .route()
            .setBody(constant("Welcome to java techie"))

        rest()
            .get("/orders")
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .route()
            .setBody { service.getOrders()}

        rest()
            .post("/addOrder")
            .consumes(MediaType.APPLICATION_JSON_VALUE)
            .type(Order::class.java)
            .outType(Order::class.java)
            .route()
            .process(processor)
            .endRest();


        // timer
//        from("timer:first-timer")  // queue
//            .log("\${body}")
//            .transform().constant("My Constant Message")
//            .log("\${body}")
//            .bean(currentTimeBean)
//            .log("\${body}")
//            .bean(logginComponent)
//            .process(SimpleLoggingProcessor())
//            .to("log:first-timer"); // database
//
//        from("file:files/input")
//            .log("\${body}")
//            .to("file:files/output")

//        from("timer:active-mq-timer?period=10000")
//            .log("activemq............")
//            .transform().constant("My message for Active MQ")
//            .to("activemq:my-activemq-queue")




    }
}