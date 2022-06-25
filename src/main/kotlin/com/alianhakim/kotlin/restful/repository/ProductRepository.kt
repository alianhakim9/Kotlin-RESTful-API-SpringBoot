package com.alianhakim.kotlin.restful.repository

import com.alianhakim.kotlin.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {

}