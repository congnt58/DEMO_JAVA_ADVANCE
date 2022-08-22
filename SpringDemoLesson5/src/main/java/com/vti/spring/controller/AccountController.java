package com.vti.spring.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.spring.entity.Account;
import com.vti.spring.entity.dto.AccountDTO;
import com.vti.spring.entity.filter.AccountFilter;
import com.vti.spring.entity.form.AccountFormCreate;
import com.vti.spring.service.IAccountService;

@RestController
@RequestMapping("/v1/api/accounts")
@CrossOrigin("*")
@Validated
public class AccountController {
	@Autowired
	IAccountService service;
	
	@Autowired
	ModelMapper mapper;

	public AccountController() {
		
	}

	@GetMapping
	public Page<AccountDTO> getAll(AccountFilter filter, Pageable pageable) {
		Page<Account> pageAccount = service.getAllAccount(filter, pageable);
		List<Account> listAcc = pageAccount.getContent();
		
		System.out.println("Size = " + listAcc.size());
		
		for (Account account : listAcc) {
			System.out.println(account);
		}
		
		List<AccountDTO> listDtos = mapper.map(pageAccount.getContent(), 
				new TypeToken<List<AccountDTO>>() {}.getType());
		
		return new PageImpl<>(listDtos, pageable, pageAccount.getTotalElements());
	}

	// GET - Lấy dữ liệu
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(name = "id") int id) {
		Account account;
		try {
			account = service.getById(id);
			AccountDTO accountDTO = mapper.map(account, AccountDTO.class);
			return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("ID khong ton tai", HttpStatus.BAD_REQUEST);
		}
	}

	// POST - Thêm
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountFormCreate form) {
		Account account = service.createAccount(form);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	// PUT - Sửa
	@PutMapping("/{id}")
	public Object updateAccount(@PathVariable(name = "id") int id,
			@RequestParam(name = "username", required = false) String newUsername,
			@RequestParam(name = "email", required = false) String newMail) {
		Account account;
		try {
			account = service.updateAccount(id, newUsername, newMail);
			return account;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	// DELETE - Xóa
	@DeleteMapping("/{iddd}")
	public String deleteAccount(@PathVariable(name = "iddd") int id) {
		try {
			service.deleteAccount(id);
			return "Delete success";
		} catch (Exception e) {
			return "Detele fail => " + e.getMessage();
		}
	}

}
