package com.example.transfers.service.impl;

import com.example.transfers.entity.external.Player;
import com.example.transfers.repository.external.PlayerRepository;
import com.example.transfers.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
	private final PlayerRepository playerRepository;

	@Override
	public Player findById(Integer id) {
		return playerRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("PlayerService.notFound"));
	}

	@Override
	public Player update(Player player) {
		return playerRepository.update(player);
	}
}
