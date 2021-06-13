package com.example.teams.controller;

import com.example.teams.entity.*;
import com.example.teams.service.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
	private final TeamService teamService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllTeams")
	public ResponseEntity<List<Team>> getAllTeams() {
		return ResponseEntity.ok(teamService.findAll());
	}

	@GetMapping("/{teamId}")
	@ApiOperation(value = "", nickname = "getTeamById")
	public ResponseEntity<Team> getTeamById(@PathVariable Integer teamId) {
		return ResponseEntity.ok(teamService.findById(teamId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveTeam")
	public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
		return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(team));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateTeam")
	public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
		return ResponseEntity.ok(teamService.update(team));
	}

	@DeleteMapping("/{teamId}")
	@ApiOperation(value = "", nickname = "deleteTeamById")
	public void deleteTeamById(@PathVariable Integer teamId) {
		teamService.deleteById(teamId);
	}

}

