package com.yunjae.camel.microservice.exception

class CamelCustomException(message:String): Exception(message) {
    companion object {
        private val serialVersionUid: Long = 1
    }
}