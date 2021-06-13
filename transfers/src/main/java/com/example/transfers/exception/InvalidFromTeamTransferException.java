package com.example.transfers.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(code = BAD_REQUEST, reason = "Player is not in the team specified or doesn't have a team to be transferred from")
public class InvalidFromTeamTransferException extends RuntimeException {
}
