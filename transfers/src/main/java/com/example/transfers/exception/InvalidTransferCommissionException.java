package com.example.transfers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Transfer commission cannot be less than 0% or greater than 10%")
public class InvalidTransferCommissionException extends RuntimeException {
}
