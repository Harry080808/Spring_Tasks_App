package com.Harry.Tasks.domain.dto;

public record ErrorResponse(
			int status,
			String message,
			String details
	) {

}
