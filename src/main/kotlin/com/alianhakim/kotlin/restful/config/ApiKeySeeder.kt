package com.alianhakim.kotlin.restful.config

import com.alianhakim.kotlin.restful.entity.ApiKey
import com.alianhakim.kotlin.restful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(
    private val apiKeyRepository: ApiKeyRepository
) : ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apiKey)) {
            val apiKey = ApiKey(id = apiKey)
            apiKeyRepository.save(apiKey)
        }
    }
}