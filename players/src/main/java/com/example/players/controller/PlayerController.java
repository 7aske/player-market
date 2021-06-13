package com.example.players.controller;

import com.example.players.entity.*;
import com.example.players.service.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
	private final PlayerService playerService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllPlayers")
	public ResponseEntity<List<Player>> getAllPlayers() {
		return ResponseEntity.ok(playerService.findAll());
	}

	@GetMapping("/{playerId}")
	@ApiOperation(value = "", nickname = "getPlayerById")
	public ResponseEntity<Player> getPlayerById(@PathVariable Integer playerId) {
		return ResponseEntity.ok(playerService.findById(playerId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "savePlayer")
	public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
		return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(player));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updatePlayer")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
		return ResponseEntity.ok(playerService.update(player));
	}

	@DeleteMapping("/{playerId}")
	@ApiOperation(value = "", nickname = "deletePlayerById")
	public void deletePlayerById(@PathVariable Integer playerId) {
		playerService.deleteById(playerId);
	}

}

