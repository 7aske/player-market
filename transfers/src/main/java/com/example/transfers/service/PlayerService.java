package com.example.transfers.service;

import com.example.transfers.entity.external.Player;
import com.example.transfers.entity.external.Team;

public interface PlayerService {
	Player findById(Integer id);

	Player update(Player player);
}
