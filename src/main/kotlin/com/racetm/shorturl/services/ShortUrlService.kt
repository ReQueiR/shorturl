package com.racetm.shorturl.services

import com.racetm.shorturl.models.ShortUrl
import com.racetm.shorturl.repository.ShortUrlRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class ShortUrlService(private val repository: ShortUrlRepository) {
    fun shorten(orgUrl: String, alias: String? = null, expiresAt: LocalDateTime? = null): ShortUrl {
        val code = alias ?: UUID.randomUUID().toString().take(6)
        val shortened = ShortUrl(code = code, originalUrl = orgUrl, expiresAt = expiresAt)
        return repository.save(shortened)
    }

    fun resolve(code: String): String? {
        val shortUrl = repository.findByCode(code) ?: return null
        val expiresAt = shortUrl.expiresAt
        if (expiresAt != null && expiresAt.isBefore(LocalDateTime.now())) {
            return null
        }

        return shortUrl.originalUrl
    }

    fun incClicks(code: String) {
        val shortUrl = repository.findByCode(code) ?: return
        shortUrl.clicks++
        repository.save(shortUrl)
    }

    fun getStats(code: String): ShortUrl? {
        return repository.findByCode(code)
    }
}