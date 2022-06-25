package com.alianhakim.kotlin.restful.model

import java.util.*

data class ProductResponse(
    val id: String,
    var name: String,
    var price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
)
