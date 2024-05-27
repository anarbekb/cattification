package ru.balmukanov.cattification.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bot")
data class BotProperty(
    var token: String,
)
