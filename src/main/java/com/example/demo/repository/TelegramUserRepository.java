package com.example.demo.repository;

import com.example.demo.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
}
