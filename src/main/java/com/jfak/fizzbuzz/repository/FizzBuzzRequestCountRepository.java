package com.jfak.fizzbuzz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jfak.fizzbuzz.model.FizzBuzzRequestCount;
import com.jfak.fizzbuzz.model.EntryId;


@Repository
public interface FizzBuzzRequestCountRepository  extends JpaRepository<FizzBuzzRequestCount, EntryId> {

	Optional<FizzBuzzRequestCount> findByEntryId(EntryId entryId);
	
	Optional<FizzBuzzRequestCount> findTopByOrderByTotalDesc();

}
