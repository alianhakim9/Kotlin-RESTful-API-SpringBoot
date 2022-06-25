package com.alianhakim.kotlin.restful.service

import com.alianhakim.kotlin.restful.entity.Product
import com.alianhakim.kotlin.restful.model.CreateProductRequest
import com.alianhakim.kotlin.restful.model.ProductResponse
import com.alianhakim.kotlin.restful.repository.ProductRepository
import com.alianhakim.kotlin.restful.validation.ValidationUtil
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)
        val newProduct = Product(
            id = createProductRequest.id,
            name = createProductRequest.name,
            price = createProductRequest.price,
            quantity = createProductRequest.quantity,
            createdAt = Date(),
            updatedAt = null
        )
        productRepository.save(newProduct)
        return convertProductToProductResponse(newProduct)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        } else {
            return convertProductToProductResponse(product)
        }
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}