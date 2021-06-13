package com.example.transfers.repository.external.impl;


import com.example.transfers.entity.external.Player;
import com.example.transfers.repository.external.AbstractExternalRepository;
import com.example.transfers.repository.external.PlayerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.Optional;

@Repository
public class PlayerRepositoryImpl extends AbstractExternalRepository<Player> implements PlayerRepository {
	@Value("${players.service.url}")
	private String playerServiceUrl;

	public PlayerRepositoryImpl() {
		super(Player.class);
	}

	@Override
	public Optional<Player> findById(Integer id) {
		URI uri = getURI(playerServiceUrl, String.valueOf(id));
		return get(uri);
	}

	@Override
	public Player update(Player player) {
		URI uri = getURI(playerServiceUrl);
		return update(uri, player).orElse(null);
	}
}
