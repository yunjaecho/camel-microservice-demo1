package com.yunjae.camel.microservice.route

import com.yunjae.camel.microservice.bean.dynamic.ProviderDynamicRoute
import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.rest.RestBindingMode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component


/*
 * Created By  : mydata-platform
 * Description : 
 * Author                    Date                     Time
 * ------------------       --------------            ------------------
 * YunJae.Cho                2021/06/28                  9:13 오전
 */


//@Component
class ChoiceRouteBuilder(context: CamelContext?): RouteBuilder(context) {
    override fun configure() {

        restConfiguration()
            .component("servlet")
            .port(8080)
            .host("localhost")
            .bindingMode(RestBindingMode.json);

        rest()
            .get("/provider")
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .route()
            .setHeader("PROVIDER_TYPE", constant("GCP"))
//            .dynamicRouter(method(ProviderDynamicRoute::class.java), "route")
            .choice()
                .`when`(header("PROVIDER_TYPE").isEqualTo("GCP"))
                    .to("direct:gcp")
                .`when`(header("PROVIDER_TYPE").isEqualTo("AWS"))
                    .to("direct:aws")
                .otherwise()
                    .log("Other received")
                    .to("direct:other")
            .end()


        from("direct:dynamicProvider") // use a bean as the dynamic router
            .choice()
                .`when`(header("PROVIDER_TYPE").isEqualTo("GCP"))
                    .to("direct:gcp")
                    .process {
                        val body = mapOf("PROVIDER_TYPE" to "GCP")
                        it.message.body = body
                    }
                .`when`(header("PROVIDER_TYPE").isEqualTo("AWS"))
                    .to("direct:aws")
                    .process {
                        val body = mapOf("PROVIDER_TYPE" to "AWS")
                        it.message.body = body
                    }
                .otherwise()
                    .log("Other received")
                    .to("direct:other")
                    .setBody { it.message.body = "PROVIDER_TYPE OTHER" }
//            .dynamicRouter(method(ProviderDynamicRoute::class.java, "route"))

        from("direct:gcp")
            .log("In direct:a with Provider  GCP")

        from("direct:aws")
            .log("In direct:a with Provider  AWS")

        from("direct:other")
            .log("In direct:a with Provider  Other")

//        from("timer:first-timer")  // queue
//            .log("\${body}")
//            .transform().constant("My Constant Message")
//            .log("\${body}")
    }
}