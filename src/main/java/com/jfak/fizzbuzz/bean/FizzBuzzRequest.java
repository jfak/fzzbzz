package com.jfak.fizzbuzz.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request object to generate the FizzBuzz sequence")
public class FizzBuzzRequest {

	@Schema(description = "int1 parameter (multiple for str1)", example = "3")
	private int int1;
	@Schema(description = "int2 parameter (multiple for str2)", example = "5")
	private int int2;
	@Schema(description = "String associated with int1. Max length allowed = 30 caracters", example = "Fizz")
	private String str1;
	@Schema(description = "String associated with int2. Max length allowed = 30 caracters", example = "Buzz")
	private String str2;
	@Schema(description = "Limit of the sequence (end of the sequence). Max value allowed: 1.000.000", example = "15")
	private int limit;


}
