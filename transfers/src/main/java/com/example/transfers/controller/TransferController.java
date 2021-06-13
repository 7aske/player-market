package com.example.transfers.controller;

import com.example.transfers.entity.Transfer;
import com.example.transfers.service.TransferService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {
	private final TransferService transferService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllTransfers")
	public ResponseEntity<List<Transfer>> getAllTransfers() {
		return ResponseEntity.ok(transferService.findAll());
	}

	@GetMapping("/{transferId}")
	@ApiOperation(value = "", nickname = "getTransferById")
	public ResponseEntity<Transfer> getTransferById(@PathVariable Integer transferId) {
		return ResponseEntity.ok(transferService.findById(transferId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveTransfer")
	public ResponseEntity<Transfer> saveTransfer(@RequestBody Transfer transfer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transferService.save(transfer));
	}

	@DeleteMapping("/{transferId}")
	@ApiOperation(value = "", nickname = "deleteTransferById")
	public void deleteTransferById(@PathVariable Integer transferId) {
		transferService.deleteById(transferId);
	}

}