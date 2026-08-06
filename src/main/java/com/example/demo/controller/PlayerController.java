package com.example.demo.controller;

import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players") // Базовый URL для всех запросов к игрокам
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Обработка POST-запроса (создание данных)
    @PostMapping
    public Player registerPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // Обработка GET-запроса (получение данных)
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
