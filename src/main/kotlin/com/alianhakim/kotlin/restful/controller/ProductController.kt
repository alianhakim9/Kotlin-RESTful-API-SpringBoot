package com.alianhakim.kotlin.restful.controller

import com.alianhakim.kotlin.restful.model.CreateProductRequest
import com.alianhakim.kotlin.restful.model.ProductResponse
import com.alianhakim.kotlin.restful.model.UpdateProductRequest
import com.alianhakim.kotlin.restful.model.WebResponse
import com.alianhakim.kotlin.restful.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/v1/products"])
class ProductController(
    private val productService: ProductService
) {
    @PostMapping(
        produces = ["application/json"], consumes = ["application/json"]
    )
    fun createProduct(
        @RequestBody createProductResponse: CreateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = productService.create(createProductResponse)
        return convertProductToWebResponse(productResponse)
    }

    @GetMapping(
        "{id_product}",
        produces = ["application/json"]
    )
    fun getProduct(
        @PathVariable("id_product") id: String
    ): WebResponse<ProductResponse> {
        val product = productService.get(id)
        return convertProductToWebResponse(product)
    }

    @PutMapping(
        "{id_product}",
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("id_product") id: String,
        @RequestBody updateProductRequest: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val product = productService.update(id, updateProductRequest)
        return convertProductToWebResponse(product)
    }

    private fun convertProductToWebResponse(product: ProductResponse): WebResponse<ProductResponse> {
        return WebResponse(
            code = 200,
            status = "OK",
            data = product
        )
    }
}