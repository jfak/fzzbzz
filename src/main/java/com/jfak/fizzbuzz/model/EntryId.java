package com.jfak.fizzbuzz.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class EntryId implements Serializable {

    private static final long serialVersionUID = 1L; 

	private int int1;
	
	private int int2;
	
	private int limitValue;
	
	private String str1;
	
	private String str2;
    
}
