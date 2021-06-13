package com.example.teams.service.impl;

import com.example.teams.entity.*;
import com.example.teams.repository.TeamRepository;
import com.example.teams.service.TeamService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TeamServiceImpl implements TeamService {
	private final TeamRepository teamRepository;

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Team findById(Integer teamId) {
		return teamRepository.findById(teamId)
				.orElseThrow(() -> new NoSuchElementException("TeamService.notFound"));
	}

	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Team update(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void deleteById(Integer teamId) {
		teamRepository.deleteById(teamId);
	}


}