package com.example.transfers.repository.external.impl;


import com.example.transfers.entity.external.Team;
import com.example.transfers.repository.external.AbstractExternalRepository;
import com.example.transfers.repository.external.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.Optional;

@Repository
public class TeamRepositoryImpl extends AbstractExternalRepository<Team> implements TeamRepository {
	@Value("${teams.service.url}")
	private String teamServiceUrl;

	public TeamRepositoryImpl() {
		super(Team.class);
	}

	@Override
	public Optional<Team> findById(Integer id) {
		URI uri = getURI(teamServiceUrl, String.valueOf(id));
		return get(uri);
	}
}
