package com.yunjae.camel.microservice.bean.dynamic

import org.apache.camel.Header
import org.springframework.stereotype.Component


/*
 * Created By  : mydata-platform
 * Description : 
 * Author                    Date                     Time
 * ------------------       --------------            ------------------
 * YunJae.Cho                2021/06/28                  11:06 오전
 */

class ProviderDynamicRoute {
    fun route(body: String, @Header("PROVIDER_TYPE") providerType: String? ): String {

        println(" PROVIDER_TYPE : $providerType ")

        return when (providerType) {
            "GCP" -> "direct:gcp"
            "AWS" -> "direct:aws"
            else -> "direct:other"
        }

    }



// public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
//
// }
}