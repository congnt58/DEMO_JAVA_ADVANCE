package com.vti.spring.entity;

import java.util.List;

import javax.persistence.Transient;

public class Department {

	@Transient
	private List<Account> accounts;

}
