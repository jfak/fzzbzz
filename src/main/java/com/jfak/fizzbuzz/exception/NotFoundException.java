package com.jfak.fizzbuzz.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final String helpUrl;
	private final String uri;
	
	public NotFoundException(String message, String helpUrl, String uri) {
		 super("Bad request: " + message);
		 this.message = message;
		 this.helpUrl = helpUrl;
		 this.uri = uri;
	}
	
}
