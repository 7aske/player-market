package com.example.transfers.service;

import com.example.transfers.entity.external.Player;
import com.example.transfers.entity.external.Team;
import com.example.transfers.exception.InvalidFromTeamTransferException;
import com.example.transfers.exception.InvalidTransferCommissionException;
import com.example.transfers.exception.SameTeamTransferException;
import com.example.transfers.service.impl.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class TransferServiceTests {
	@Autowired
	TransferServiceImpl transferService;

	Team toTeam;
	Team fromTeam;
	Player player;
	Float commission;

	// valid data
	@BeforeEach
	void setup() {
		toTeam = new Team();
		toTeam.setId(1);
		toTeam.setName("Team1");

		fromTeam = new Team();
		fromTeam.setId(2);
		fromTeam.setName("Team2");

		player = new Player();
		player.setId(1);
		player.setFirstName("Test");
		player.setLastName("Testovic");
		player.setAge(18);
		player.setTeamId(fromTeam.getId());
		player.setMonthsExperience(10);

		commission = 10.0f;
	}

	@Test
	void test_ageLessThanEqualsToZero_shouldThrow() {
		player.setAge(0);
		assertThrows(IllegalArgumentException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, toTeam, player, commission));
	}

	@Test
	void test_monthsOfExperienceLessThanZero_shouldThrow() {
		player.setMonthsExperience(-1);
		assertThrows(IllegalArgumentException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, toTeam, player, commission));
	}

	@Test
	void test_playerTransferringToSameTeam_shouldThrow() {
		assertThrows(SameTeamTransferException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, fromTeam, player, commission));
	}

	@Test
	void test_playerInvalidFromTeam_shouldThrow(){
		player.setTeamId(Integer.MAX_VALUE);
		assertThrows(InvalidFromTeamTransferException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, fromTeam, player, commission));
	}

	@Test
	void test_commissionLessThanZero_shouldThrow(){
		commission = -10.0f;
		assertThrows(InvalidTransferCommissionException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, toTeam, player, commission));
	}

	@Test
	void test_commissionGreaterThanTen_shouldThrow(){
		commission = 100.0f;
		assertThrows(InvalidTransferCommissionException.class,
				() -> transferService.calculatePlayerTransferFee(fromTeam, toTeam, player, commission));
	}

	@Test
	void test_validData() {
		Float price = transferService.calculatePlayerTransferFee(fromTeam, toTeam, player, commission);
		assertEquals(61111.10f, price,0.01);
	}
}
