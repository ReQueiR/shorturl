package com.racetm.rApp.controllers

import com.racetm.rApp.dto.ShortUrlRequest
import com.racetm.rApp.models.ShortUrl
import com.racetm.rApp.services.ShortUrlService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.net.URI

@RestController
class ShortUrlController(private val urlService: ShortUrlService) {

    @PostMapping("/shorten")
    fun shorten(@Valid @RequestBody request: ShortUrlRequest): ResponseEntity<ShortUrl> {
        val shortened = urlService.shorten(request.url, request.alias, request.expiresAt)

        return ResponseEntity.ok(shortened)
    }

    @GetMapping("/{code:[a-zA-Z0-9]{2,}}")
    fun redirect(@PathVariable code: String) : ResponseEntity<Unit> {
        val orgUrl = urlService.resolve(code) ?: return ResponseEntity.notFound().build()

        urlService.incClicks(code)

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(orgUrl))
            .build()
    }

    @GetMapping("/stats/{code}")
    fun stats(@PathVariable code: String): ResponseEntity<ShortUrl> {
        val shortUrl = urlService.getStats(code) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(shortUrl)
    }
}