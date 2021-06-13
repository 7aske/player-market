package com.example.teams.service;

import com.example.teams.entity.*;
import java.util.Collection;
import java.util.List;

public interface TeamService {

	List<Team> findAll();

	Team save(Team team);

	Team update(Team team);

	Team findById(Integer teamId);

	void deleteById(Integer teamId);

}