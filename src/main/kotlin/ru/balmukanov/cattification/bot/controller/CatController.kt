package ru.balmukanov.cattification.bot.controller

import com.github.kshashov.telegram.api.MessageType
import com.github.kshashov.telegram.api.TelegramMvcController
import com.github.kshashov.telegram.api.bind.annotation.BotController
import com.github.kshashov.telegram.api.bind.annotation.BotRequest
import com.pengrad.telegrambot.model.Chat
import com.pengrad.telegrambot.model.User
import com.pengrad.telegrambot.request.SendMessage
import ru.balmukanov.cattification.config.property.BotProperty

@BotController
class CatController(private val botProperty: BotProperty) : TelegramMvcController {

    override fun getToken(): String {
        return botProperty.token
    }

    @BotRequest(value = ["/meow"], type = [MessageType.CALLBACK_QUERY, MessageType.MESSAGE])
    fun meow(user: User, chat: Chat): SendMessage {
        return SendMessage(chat.id(), "Meow")
    }
}
