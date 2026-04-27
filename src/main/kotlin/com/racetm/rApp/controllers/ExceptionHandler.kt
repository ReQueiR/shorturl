package com.racetm.rApp.controllers

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validExceptions(except: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val errors = except.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Validation error!") }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body (
            mapOf (
                "status" to 400,
                "errors" to errors
            )
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDuplicates(): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            mapOf (
                "status" to 409,
                "errors" to "The alias is already taken!"
            )
        )
    }
}