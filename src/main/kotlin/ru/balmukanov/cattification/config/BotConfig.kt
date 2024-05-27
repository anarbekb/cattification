package ru.balmukanov.cattification.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.balmukanov.cattification.config.property.BotProperty

@Configuration
@EnableConfigurationProperties(BotProperty::class)
class BotConfig
