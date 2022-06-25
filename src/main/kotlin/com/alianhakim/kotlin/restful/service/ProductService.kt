package com.alianhakim.kotlin.restful.service

import com.alianhakim.kotlin.restful.model.CreateProductRequest
import com.alianhakim.kotlin.restful.model.ProductResponse

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse
}