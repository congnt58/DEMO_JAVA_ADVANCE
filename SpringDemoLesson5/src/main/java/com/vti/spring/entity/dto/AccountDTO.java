package com.vti.spring.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
	
//	public AccountDTO(Account account) {
//		this.id = account.getId();
//		this.email = account.getEmail();
//		this.username = account.getUsername();
//	}

	private int id;
	
	//@JsonProperty("uname")
	private String username;
	
//	@JsonProperty("mail")
	private String email;
	
//	private String departmentName;

}
