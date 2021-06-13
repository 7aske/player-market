package com.example.transfers.service.impl;

import com.example.transfers.entity.Transfer;
import com.example.transfers.entity.external.Player;
import com.example.transfers.entity.external.Team;
import com.example.transfers.exception.InvalidFromTeamTransferException;
import com.example.transfers.exception.InvalidTransferCommissionException;
import com.example.transfers.exception.SameTeamTransferException;
import com.example.transfers.repository.TransferRepository;
import com.example.transfers.service.PlayerService;
import com.example.transfers.service.TeamService;
import com.example.transfers.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
	private final TransferRepository transferRepository;
	private final PlayerService playerService;
	private final TeamService teamService;
	private static final int TRANSFER_FEE_COEFFICIENT = 100_000;

	@Override
	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}

	@Override
	public Transfer findById(Integer transferId) {
		return transferRepository.findById(transferId)
				.orElseThrow(() -> new NoSuchElementException("TransferService.notFound"));
	}

	@Override
	public Transfer save(Transfer transfer) {
		// by calling services we throw of they dont exist
		Player player = playerService.findById(transfer.getPlayerId());
		Team toTeam = teamService.findById(transfer.getToTeamId());
		Team fromTeam = teamService.findById(transfer.getFromTeamId());

		Float transferFee = calculatePlayerTransferFee(fromTeam, toTeam, player, transfer.getCommission());
		transfer.setTransferFee(transferFee);

		Transfer saved = transferRepository.save(transfer);

		player.setTeamId(toTeam.getId());
		playerService.update(player);

		return saved;
	}


	public Float calculatePlayerTransferFee(Team fromTeam, Team toTeam, Player player, Float commission) {
		Integer monthsOfExperience = player.getMonthsExperience();
		Integer age = player.getAge();

		if (player.getTeamId() == null)
			throw new InvalidFromTeamTransferException();

		if (!player.getTeamId().equals(fromTeam.getId()))
			throw new InvalidFromTeamTransferException();

		if (player.getTeamId().equals(toTeam.getId()))
			throw new SameTeamTransferException();

		if (monthsOfExperience == null || monthsOfExperience < 0)
			throw new IllegalArgumentException("Parameter 'monthsOfExperience' cannot be less than zero");

		if (age == null || age <= 0)
			throw new IllegalArgumentException("Parameter 'age' cannot be less or equal to zero");

		if (commission == null || !(0 <= commission && commission <= 10))
			throw new InvalidTransferCommissionException();

		float baseFee = monthsOfExperience * TRANSFER_FEE_COEFFICIENT / (float) age;
		return baseFee + baseFee * (commission / 100.0f);
	}

	@Override
	public Transfer update(Transfer transfer) {
		return transferRepository.save(transfer);
	}

	@Override
	public void deleteById(Integer transferId) {
		transferRepository.deleteById(transferId);
	}


}
