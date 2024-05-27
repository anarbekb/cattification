package ru.balmukanov.cattification.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.balmukanov.cattification.config.property.CatProperty

@Configuration
@EnableConfigurationProperties(CatProperty::class)
class CatConfig
