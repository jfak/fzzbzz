package com.jfak.fizzbuzz.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="fizzbuzz_count")
public class FizzBuzzRequestCount {

	@EmbeddedId
    private EntryId entryId;
		
	private int total;
	
}
