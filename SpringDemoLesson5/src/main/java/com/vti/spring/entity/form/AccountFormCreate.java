package com.vti.spring.entity.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFormCreate {
	private String username;
	private String email;
	private int departmentId;
	private int positonId;

}
