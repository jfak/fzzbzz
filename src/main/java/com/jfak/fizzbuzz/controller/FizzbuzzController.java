package com.jfak.fizzbuzz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jfak.fizzbuzz.bean.FizzBuzzRequest;
import com.jfak.fizzbuzz.bean.StatsResponse;
import com.jfak.fizzbuzz.model.FizzBuzzRequestCount;
import com.jfak.fizzbuzz.service.FizzBuzzRequestCountService;
import com.jfak.fizzbuzz.service.FizzbuzzService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FizzbuzzController {

	Logger logger = LoggerFactory.getLogger(FizzbuzzController.class);

	private final FizzbuzzService fizzbuzzService; 
	private final FizzBuzzRequestCountService fizzBuzzRequestCountService;

    @Operation(summary = "Generate a FizzBuzz sequence", 
            description = "Generates a sequence of numbers up to the specified limit, replacing multiples of the given parameters with the corresponding strings (str1 and str2).")
	 @ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "successful generated sequence"),
	     @ApiResponse(responseCode = "400", description = "invalid parameters")
	 })
	@PostMapping("/fizzbuzz")
	public ResponseEntity<String> getFizzbuzz(@RequestBody FizzBuzzRequest entry, HttpServletRequest request) {	
		String result = fizzbuzzService.getResult(entry, request.getRequestURI());
		// then we persist the request for stats if there is no error
		fizzBuzzRequestCountService.incrementFizzBuzzRequestCount(entry);
		return ResponseEntity.ok(result);
	}
	
    
    @Operation(summary = "Provide the most frequent request for the specified parameters", 
            description = "Provides the total number of the  most frequent request for the specified parameters.")
	 @ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "successful request"),
	     @ApiResponse(responseCode = "400", description = "invalid parameters"),
	     @ApiResponse(responseCode = "404", description = "Data not available. You must generate some FizzBuzz requests before.")
	 })
	@GetMapping("/top")
	public ResponseEntity<StatsResponse> getTopRequest(HttpServletRequest request) {
		FizzBuzzRequestCount entryCount = fizzBuzzRequestCountService.getTopRequest(request.getRequestURI());
		StatsResponse out = StatsResponse.builder()
						.int1(entryCount.getEntryId().getInt1())
						.int2(entryCount.getEntryId().getInt2())
						.str1(entryCount.getEntryId().getStr1())
						.str2(entryCount.getEntryId().getStr2())
						.limit(entryCount.getEntryId().getLimitValue())
						.total(entryCount.getTotal())	
						.build();
		return ResponseEntity.ok(out);
	}
	
	
}
