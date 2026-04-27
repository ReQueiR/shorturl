package com.racetm.rApp.repository

import com.racetm.rApp.models.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepository: JpaRepository<ShortUrl, Long> {
    fun findByCode(code: String): ShortUrl?

}