package com.kushals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested number of words can't be returned, number exceeds total number of words")
public class InvalidWordNumberCountException extends Exception {

	public InvalidWordNumberCountException(String errorMessage) {
		super(errorMessage);
	}

}
