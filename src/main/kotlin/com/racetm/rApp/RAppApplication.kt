package com.racetm.rApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RAppApplication

fun main(args: Array<String>) {
	runApplication<RAppApplication>(*args)
}
