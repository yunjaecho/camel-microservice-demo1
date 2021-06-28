package com.yunjae.camel.microservice.controller

import com.yunjae.camel.microservice.model.Order
import org.apache.camel.ProducerTemplate
import org.apache.camel.support.DefaultExchange
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/*
 * Created By  : mydata-platform
 * Description : 
 * Author                    Date                     Time
 * ------------------       --------------            ------------------
 * YunJae.Cho                2021/06/28                  11:25 오전
 */


@RestController
@RequestMapping("/api")
class CamelController(val producerTemplate: ProducerTemplate) {

    @PostMapping("/provider")
    fun providerTest(@RequestBody order: Order): Any? {

        val exchange = DefaultExchange(producerTemplate.camelContext)
        exchange.getIn().setHeader("PROVIDER_TYPE", "AWS")
        exchange.getIn().body = order


        val headerMap = HashMap<String, Any>()
        headerMap.put("PROVIDER_TYPE", "AWS")
        val result = producerTemplate.send("direct:dynamicProvider", exchange)
        return result.message.body
    }


}