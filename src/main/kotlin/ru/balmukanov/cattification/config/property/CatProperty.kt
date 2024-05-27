package ru.balmukanov.cattification.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.LocalDate

@ConfigurationProperties(prefix = "cat")
data class CatProperty(
    val weight: Double,
    val dateOfBirth: LocalDate,
)
