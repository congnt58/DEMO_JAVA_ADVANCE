package com.vti.spring.entity.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilter {
	
	private String username;
	//@Getter(value = AccessLevel.NONE)
	private String email;
	
	private Integer minID;
	
	private Integer maxID;
	
	
}
