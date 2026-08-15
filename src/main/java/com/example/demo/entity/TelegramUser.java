package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "telegram_users")
public class TelegramUser {

    @Id
    private Long chatId;

    private String firstName;
    private String username;

    public TelegramUser() {
    }

    public TelegramUser(Long chatId, String firstName, String username) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.username = username;
    }

    public Long getChatId() { return chatId; }
    public void setChatId(Long chatId) { this.chatId = chatId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
