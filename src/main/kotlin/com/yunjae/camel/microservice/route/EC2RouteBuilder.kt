package com.yunjae.camel.microservice.route

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component


/*
 * Created By  : mydata-platform
 * Description : 
 * Author                    Date                     Time
 * ------------------       --------------            ------------------
 * YunJae.Cho                2021/06/28                  1:57 오후
 */

@Component
class EC2RouteBuilder(context: CamelContext?) : RouteBuilder(context) {
    override fun configure() {

    }
}