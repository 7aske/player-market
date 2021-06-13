package com.example.transfers.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(code = BAD_REQUEST, reason = "Can not trasfer to current team")
public class SameTeamTransferException extends RuntimeException {
}
