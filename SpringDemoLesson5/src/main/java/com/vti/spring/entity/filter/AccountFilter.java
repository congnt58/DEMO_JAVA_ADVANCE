package com.vti.spring.entity.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AccountFilter {
	
	private String username;
	//@Getter(value = AccessLevel.NONE)
	private String email;
	
	private String fullName;
	
	private Integer minID ;
	
	private Integer maxID;
	
	private String derpatmentName;
	
	
}
