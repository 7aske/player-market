package com.example.teams.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class ErrorController {

	@ExceptionHandler({
			NoSuchElementException.class,
	})
	protected ResponseEntity<ResponseError> handleNotFound(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return getResponseError(ex.getMessage(), request, status);
	}

	@ExceptionHandler({
			IllegalArgumentException.class,
	})
	protected ResponseEntity<ResponseError> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return getResponseError(ex.getMessage(), request, status);
	}


	@ExceptionHandler({
			IllegalStateException.class,
			Exception.class
	})
	protected ResponseEntity<ResponseError> handleInternalServerError(RuntimeException ex, HttpServletRequest request) {
		ex.printStackTrace();

		ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
		if (responseStatus != null) {
			String message = responseStatus.reason();
			if (ex.getMessage() != null)
				message = String.format("%s: %s", responseStatus.reason(), ex.getMessage());

			return getResponseError(message, request, responseStatus.code());
		}
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return getResponseError(ex.getMessage(), request, status);
	}

	private ResponseEntity<ResponseError> getResponseError(String message, HttpServletRequest request, HttpStatus status) {
		ResponseError errorInfo = new ResponseError(status, request, message);
		return ResponseEntity.status(errorInfo.getCode()).body(errorInfo);
	}

	@Data
	@AllArgsConstructor
	public static final class ResponseError {
		private String message;
		private Integer code;
		private String url;
		private String exception;

		public ResponseError(HttpStatus status, HttpServletRequest request, String exception) {
			this.message = status.getReasonPhrase();
			this.code = status.value();
			this.url = request.getRequestURI();
			this.exception = exception;
		}
	}
}
