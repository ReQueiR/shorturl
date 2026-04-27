package com.racetm.rApp.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

data class ShortUrlRequest (
    @field: NotBlank(message = "Url can not be blank!")
    @field: Pattern (
        regexp = "^https?://.*",
        message = "Url should start with https:// or https://")
    val url: String,

    @field:Pattern (
        regexp = "^[a-zA-Z0-9\\-_]+$",
        message = "Alias can only contain numbers, letters, hyphens and underscores"
    )
    val alias: String?,
    val expiresAt: LocalDateTime?
) {

}