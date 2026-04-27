package com.racetm.shorturl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShortUrlApp

fun main(args: Array<String>) {
	runApplication<ShortUrlApp>(*args)
}
