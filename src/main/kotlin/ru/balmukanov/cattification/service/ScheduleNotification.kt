package ru.balmukanov.cattification.service

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.balmukanov.cattification.config.property.BotProperty
import ru.balmukanov.cattification.config.property.CatProperty
import java.time.Duration
import java.time.LocalDate
import kotlin.math.abs

@Service
class ScheduleNotification(private val botProperty: BotProperty, private val catProperty: CatProperty) {

    @Scheduled(cron = "0 8 * * * ?", zone = "PLT")
    fun scheduledBreakfast() {
        val bot = TelegramBot(botProperty.token)
        val roundGrams = Math.round(gramsPerOneTime(catProperty.weight) * 10.0) / 10.0
        bot.execute(SendMessage(320416029L, "Завтрак: $roundGrams граммов"))
    }

    @Scheduled(cron = "0 14 * * * ?", zone = "PLT")
    fun scheduledDinner() {
        val bot = TelegramBot(botProperty.token)
        val roundGrams = Math.round(gramsPerOneTime(catProperty.weight) * 10.0) / 10.0
        botProperty.notificationUsers.forEach { user ->
            bot.execute(SendMessage(user, "Обед: $roundGrams граммов"))
        }
    }

    @Scheduled(cron = "0 20 * * * ?", zone = "PLT")
    fun scheduledLunch() {
        val bot = TelegramBot(botProperty.token)
        val roundGrams = Math.round(gramsPerOneTime(catProperty.weight) * 10.0) / 10.0
        botProperty.notificationUsers.forEach { user ->
            bot.execute(SendMessage(user, "Ужин: $roundGrams граммов"))
        }
    }

    @Scheduled(cron = "0 8 7 * * ?", zone = "PLT")
    fun scheduledMonthBirthday() {
        val bot = TelegramBot(botProperty.token)
        val age: Long = abs(Duration.between(LocalDate.now().atStartOfDay(), catProperty.dateOfBirth.atStartOfDay()).toDays() / 30.41.toInt())
        botProperty.notificationUsers.forEach { user ->
            bot.execute(SendMessage(user, "Коту $age месяцев"))
        }
    }

    @Scheduled(cron = "0 8 7 10 * ?", zone = "PLT")
    fun scheduledYearBirthday() {
        val bot = TelegramBot(botProperty.token)
        val age: Long = abs(Duration.between(LocalDate.now().atStartOfDay(), catProperty.dateOfBirth.atStartOfDay()).toDays() / 30.41.toInt()) / 12
        botProperty.notificationUsers.forEach { user ->
            bot.execute(SendMessage(user, "Коту исполнилось $age"))
        }
    }

    private fun gramsPerOneTime(weight: Double): Double {
        val timePerDay = 3
        val kcalInOneGram = 4
        return (dailyNormKilocalories(weight) / timePerDay) / kcalInOneGram
    }

    private fun dailyNormKilocalories(weight: Double): Double {
        return 1.4 * ((30 * weight) + 70)
    }
}
