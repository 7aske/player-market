package com.example.transfers.repository.external;

import com.example.transfers.entity.external.Team;

import java.util.Optional;


public interface TeamRepository {
	Optional<Team> findById(Integer id);
}
