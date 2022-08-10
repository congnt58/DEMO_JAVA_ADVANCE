package com.vti.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.spring.entity.Account;
import com.vti.spring.entity.Department;
import com.vti.spring.entity.filter.AccountFilter;
import com.vti.spring.entity.form.AccountFormCreate;
import com.vti.spring.repository.DepartmentRepositoryImpl;
import com.vti.spring.repository.IAccountRepository;
import com.vti.spring.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService {

	@Autowired
	IAccountRepository repository;

	DepartmentRepositoryImpl dpRepositoryImpl;

	public AccountService() {
		dpRepositoryImpl = new DepartmentRepositoryImpl();
	}
	

	@Override
	public Page<Account> getAllAccount(AccountFilter filter, Pageable pageable) {
		
		Specification<Account> where = AccountSpecification.buildWhere(filter);
		
		return repository.findAll(where, pageable);
	}

	

	@Override
	public boolean deleteAccount(int id) throws Exception {
		if (!repository.existsById(id)) {
			throw new Exception("id Account ko ton tai");
		}

		repository.deleteById(id);
		return true;
	}

	@Override
	public Account getById(int id) throws Exception {
		if (!repository.existsById(id)) {
			throw new Exception("id Account ko ton tai");
		}
		return repository.findById(id).get();
	}

	@Override
	public Account createAccount(AccountFormCreate formCreate) {
		Account account = new Account();

		account.setUsername(formCreate.getUsername());
		account.setEmail(formCreate.getEmail());

		Department dp = dpRepositoryImpl.getDpById(formCreate.getDepartmentId());
		account.setDepartment(dp);

		return repository.save(account);
	}

	@Transactional
	@Override
	public Account updateAccount(int id, String newUsername, String newMail) throws Exception {

		if (!repository.existsById(id)) {
			throw new Exception("id Account ko ton tai");
		}

		Account account = repository.findById(id).get();

		if (newUsername != null) {
			account.setUsername(newUsername);
		}

		if (newMail != null) {
			account.setEmail(newMail);
		}

		return repository.save(account);
	}

}
