package com.vti.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.spring.entity.Account;
import com.vti.spring.entity.filter.AccountFilter;
import com.vti.spring.entity.form.AccountFormCreate;

public interface IAccountService {
	public Page<Account> getAllAccount(AccountFilter filter, Pageable pageable);
	
	public boolean deleteAccount(int id) throws Exception;

	public Account getById(int id) throws Exception;

	public Account updateAccount(int id, String newUsername, String newMail) throws Exception;

	public Account createAccount(AccountFormCreate form);
	
	
}
