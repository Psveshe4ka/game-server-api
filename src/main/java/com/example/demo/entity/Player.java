package com.example.demo.entity;
// Твой пакет может немного отличаться

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // Позже мы будем хранить здесь хэш пароля

    @Column(nullable = false)
    private int rating = 1000; // Базовый рейтинг при регистрации

    // Пустой конструктор нужен для Hibernate
    public Player() {
    }

    public Player(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    // Геттеры и сеттеры (можно сгенерировать через Alt+Insert в IDEA)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
