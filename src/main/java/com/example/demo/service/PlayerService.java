package com.example.demo.service;



import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    // Внедрение зависимости через конструктор (лучшая практика Spring)
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Метод для регистрации нового игрока
    public Player createPlayer(Player player) {
        // Проверяем, свободен ли никнейм
        Optional<Player> existingPlayer = playerRepository.findByNickname(player.getNickname());
        if (existingPlayer.isPresent()) {
            throw new RuntimeException("Игрок с никнеймом " + player.getNickname() + " уже существует!");
        }
        // Если всё ок, сохраняем в базу PostgreSQL
        return playerRepository.save(player);
    }


    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
