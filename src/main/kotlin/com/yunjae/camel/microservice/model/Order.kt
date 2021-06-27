package com.yunjae.camel.microservice.model

import java.beans.ConstructorProperties

data class Order @ConstructorProperties("id", "name", "price") constructor(val id: Int, val name: String, val price: Double)