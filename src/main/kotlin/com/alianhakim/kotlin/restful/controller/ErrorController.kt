package com.alianhakim.kotlin.restful.controller

import com.alianhakim.kotlin.restful.exception.UnAuthorizedException
import com.alianhakim.kotlin.restful.model.WebResponse
import org.hibernate.annotations.NotFound
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message.toString()
        )
    }

    @ExceptionHandler(value = [SQLIntegrityConstraintViolationException::class])
    fun sqlValidationHandler(sqlIntegrityConstraintViolationException: SQLIntegrityConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = sqlIntegrityConstraintViolationException.message.toString()
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(notfoundException: NotFoundException): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "not found"
        )
    }

    @ExceptionHandler(value = [UnAuthorizedException::class])
    fun unAuthorizedHandler(unAuthoriedException: UnAuthorizedException): WebResponse<String> {
        return WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "Please put your X-API-Key"
        )
    }
}