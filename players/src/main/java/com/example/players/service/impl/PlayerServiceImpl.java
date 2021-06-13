package com.example.players.service.impl;

import com.example.players.entity.*;
import com.example.players.repository.PlayerRepository;
import com.example.players.service.PlayerService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PlayerServiceImpl implements PlayerService {
	private final PlayerRepository playerRepository;

	@Override
	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public Player findById(Integer playerId) {
		return playerRepository.findById(playerId)
				.orElseThrow(() -> new NoSuchElementException("PlayerService.notFound"));
	}

	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player update(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public void deleteById(Integer playerId) {
		playerRepository.deleteById(playerId);
	}


}