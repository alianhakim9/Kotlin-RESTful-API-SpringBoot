package com.alianhakim.kotlin.restful.controller

import com.alianhakim.kotlin.restful.model.*
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

    @DeleteMapping(
        "{id_product}",
        produces = ["application/json"]
    )
    fun deleteProduct(
        @PathVariable("id_product") id: String
    ): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Product Deleted"
        )
    }

    @GetMapping(produces = ["application/json"])
    fun listProducts(
        @RequestParam("size", defaultValue = "10") size: Int,
        @RequestParam("page", defaultValue = "0") page: Int
    ): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page, size)
        val response = productService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    private fun convertProductToWebResponse(product: ProductResponse): WebResponse<ProductResponse> {
        return WebResponse(
            code = 200,
            status = "OK",
            data = product
        )
    }
}