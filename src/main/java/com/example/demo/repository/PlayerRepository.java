package com.example.demo.repository;


import com.example.demo.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Spring Data JPA сам напишет реализацию этого метода!
    Optional<Player> findByNickname(String nickname);
}