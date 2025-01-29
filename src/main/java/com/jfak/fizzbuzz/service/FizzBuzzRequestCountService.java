package com.jfak.fizzbuzz.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import com.jfak.fizzbuzz.bean.FizzBuzzRequest;
import com.jfak.fizzbuzz.exception.NotFoundException;
import com.jfak.fizzbuzz.model.FizzBuzzRequestCount;
import com.jfak.fizzbuzz.model.EntryId;
import com.jfak.fizzbuzz.repository.FizzBuzzRequestCountRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class FizzBuzzRequestCountService {

	private final FizzBuzzRequestCountRepository fizzBuzzRequestCountRepository;
	
	/** increment Entry count */
	public void incrementFizzBuzzRequestCount(FizzBuzzRequest bean) {
		
		EntryId entryId = getEntryId(bean);
		Optional<FizzBuzzRequestCount> optEntryCount = fizzBuzzRequestCountRepository.findByEntryId(entryId);
	
		if(optEntryCount.isPresent()) {
			FizzBuzzRequestCount entryCount = optEntryCount.get();
			entryCount.setTotal(entryCount.getTotal()+1);
			fizzBuzzRequestCountRepository.save(entryCount);
			
		} else {
			createFizzBuzzRequestCount(entryId);
		}	
		
	}
	
	/** get top request */
	public FizzBuzzRequestCount getTopRequest(String uri) {
		Optional<FizzBuzzRequestCount> optEntryCount = fizzBuzzRequestCountRepository.findTopByOrderByTotalDesc();
		if(optEntryCount.isPresent()) {
			return optEntryCount.get();
		} else {
			throw new NotFoundException("Top request not available", "", uri); 
		}
	}
	
	/** persist the request in database for first time request */
	private FizzBuzzRequestCount createFizzBuzzRequestCount(EntryId entryId) {
				
		FizzBuzzRequestCount entryCount = FizzBuzzRequestCount.builder()
								.entryId(entryId)
								.total(1)
								.build();
		
		return fizzBuzzRequestCountRepository.save(entryCount); 
	}
	
	
	/** get Id from EntryBean */
	private EntryId getEntryId(FizzBuzzRequest entry) {
		return EntryId.builder()
				.int1(entry.getInt1())
				.int2(entry.getInt2())
				.limitValue(entry.getLimit())
				.str1(entry.getStr1())
				.str2(entry.getStr2())
				.build();
	}
	
}
