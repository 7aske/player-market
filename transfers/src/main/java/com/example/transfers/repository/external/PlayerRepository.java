package com.example.transfers.repository.external;

import com.example.transfers.entity.external.Player;

import java.util.Optional;

public interface PlayerRepository {
	Optional<Player> findById(Integer id);

	Player update(Player player);
}
