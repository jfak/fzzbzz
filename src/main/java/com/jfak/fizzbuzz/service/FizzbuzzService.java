package com.jfak.fizzbuzz.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfak.fizzbuzz.bean.FizzBuzzRequest;
import com.jfak.fizzbuzz.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FizzbuzzService {
	
	/** main method: provides the attended fizzbuzz result  */
	public String getResult(FizzBuzzRequest entry, String uri) {
		
		// controls
		int limit = entry.getLimit();
		if(!isValid(entry)) {
			throw new BadRequestException("All parameters int1, int2, limit, str1 and str2 are mandatory. Int1 and int2 can't be equal to 0.", "", uri);
		}
		if( limit > 1e6) {
			throw new BadRequestException("Maximum limit allowed is 1.000.000", "", uri);
		}
		if(entry.getStr1().length() > 30 || entry.getStr2().length() > 30) {
			throw new BadRequestException("Maximum length allowed for str1 and str2 is 30 caracters.", "", uri);
		}
		
		// filters
		LinkedHashMap<Integer, String> filters = new LinkedHashMap<>();
		filters.put(entry.getInt1(), entry.getStr1());
		filters.put(entry.getInt2(), entry.getStr2());
						
		StringBuilder builder = new StringBuilder(); 
		IntStream.range(1, limit + 1).forEach(i -> builder.append(convert(i, filters)).append(","));
		
		return StringUtils.chop(builder.toString());
	}
		
	/** application of the substitution filters */
	private String convert(int i,  LinkedHashMap<Integer, String> filters) {
		StringBuilder result = new StringBuilder();  
        for (Map.Entry<Integer, String> entry : filters.entrySet()) {
            if(i % entry.getKey() == 0) { 
            	result.append(entry.getValue()); 
            }
        }
		return result.isEmpty()? String.valueOf(i): result.toString();
	}
	
	/** not null entry controls */
	private boolean isValid(FizzBuzzRequest entry) {
		return (entry.getInt1()!=0 && entry.getInt2()!=0 && entry.getLimit()!=0 && entry.getStr1()!=null && entry.getStr2()!=null);
	}
	
}
