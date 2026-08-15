package com.example.demo.bot;

import com.example.demo.entity.TelegramUser;
import com.example.demo.repository.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String botName;

    private final TelegramUserRepository userRepository;

    public TelegramBot(@Value("${telegram.bot.token}") String botToken, TelegramUserRepository userRepository) {
        super(botToken);
        this.userRepository = userRepository;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    // Вот этот метод, который принимает все сообщения
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User fromUser = update.getMessage().getFrom();

            // 1. Сохраняем пользователя в БД, если его там еще нет
            if (!userRepository.existsById(chatId)) {
                TelegramUser newUser = new TelegramUser(chatId, fromUser.getFirstName(), fromUser.getUserName());
                userRepository.save(newUser);
            }

            // 2. Обрабатываем конкретные команды
            switch (messageText) {
                case "/start":
                    sendMenu(chatId, "Добро пожаловать, " + fromUser.getFirstName() + "! Выбери действие в меню ниже 👇");
                    break;
                case "👤 Мой профиль":
                    sendMessage(chatId, "Твой Telegram ID: " + chatId + "\nИмя: " + fromUser.getFirstName());
                    break;
                case "❓ Помощь":
                    sendMessage(chatId, "Это бот-шаблон. Скоро здесь появится крутой функционал!");
                    break;
                default:
                    sendMessage(chatId, "Я не понимаю эту команду. Воспользуйся меню.");
            }
        }
    }

    // Метод для отправки простого текста
    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода клавиатуры с кнопками
    private void sendMenu(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        // Настраиваем клавиатуру
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // Кнопки будут аккуратного размера
        keyboardMarkup.setOneTimeKeyboard(false);

        // Создаем ряды кнопок
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        row1.add("👤 Мой профиль");
        row1.add("❓ Помощь");

        keyboardRows.add(row1);
        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
