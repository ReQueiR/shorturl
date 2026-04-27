package com.racetm.rApp.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "short_url")
class ShortUrl (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var code: String,
    var originalUrl: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var clicks: Int = 0,
    var expiresAt: LocalDateTime? = null
)
