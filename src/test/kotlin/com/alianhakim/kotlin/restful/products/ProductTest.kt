package com.alianhakim.kotlin.restful.products

import com.alianhakim.kotlin.restful.model.CreateProductRequest
import org.apache.coyote.Response
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.net.URI

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductTest {

    lateinit var restTemplate: RestTemplate
    lateinit var baseUrl: String
    lateinit var uri: URI
    lateinit var headers: HttpHeaders

    @LocalServerPort
    var randomServerPort: Int = 0

    @BeforeEach
    fun setup() {
        restTemplate = RestTemplate()
        baseUrl = "http://localhost:$randomServerPort/api/v1/products"
        uri = URI(baseUrl)
        headers = HttpHeaders()
    }

    @Test
    fun createProductSuccess() {
        val createProductRequest = CreateProductRequest(
            id = "P001", name = "Aerostreet", price = 120000, quantity = 10
        )
        headers.set("Content-Type", "application/json")
        headers.set("Accept", "application/json")
        val request: HttpEntity<CreateProductRequest> = HttpEntity(createProductRequest, headers)
        val result: ResponseEntity<String> = restTemplate.postForEntity(uri, request, String::class.java)
        // verify request succeed
//        Assertions.assertEquals(200, result.statusCodeValue)
        print(result.body)
    }

    @Test
    fun testValidationError() {
        val createProductRequest = CreateProductRequest(
            id = "", name = "", price = 0, quantity = -10
        )
        headers.set("Content-Type", "application/json")
        headers.set("Accept", "application/json")
        val request: HttpEntity<CreateProductRequest> = HttpEntity(createProductRequest, headers)
        val result: ResponseEntity<String> = restTemplate.postForEntity(uri, request, String::class.java)
        print(result.body)
//        Assertions.assertEquals(400, result.body.code`)
    }

    @Test
    fun testDuplicatedProduct() {
        val createProductRequest = CreateProductRequest(
            id = "P001", name = "Aerostreet", price = 120000, quantity = 10
        )
        headers.set("Content-Type", "application/json")
        headers.set("Accept", "application/json")
        val request: HttpEntity<CreateProductRequest> = HttpEntity(createProductRequest, headers)
        val result: ResponseEntity<String> = restTemplate.postForEntity(uri, request, String::class.java)
        print(result.body)
    }

    @Test
    fun testGetProduct() {
        headers.set("Accept", "application/json")
        val result: ResponseEntity<String> = restTemplate.getForEntity(uri, String::class.java)
        print(result.body)
    }
}