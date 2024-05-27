package ru.balmukanov.cattification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CattificationApplication

fun main(args: Array<String>) {
	runApplication<CattificationApplication>(*args)
}
