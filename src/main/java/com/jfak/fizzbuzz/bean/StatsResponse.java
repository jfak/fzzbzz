package com.jfak.fizzbuzz.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "Response object to provide the most frequent request of the FizzBuzz sequence")
public class StatsResponse extends FizzBuzzRequest {

	@Schema(description = "Number of total requests for the previous parameters", example = "14")
	private int total;

}
