package com.vti.spring.specification;

import org.springframework.data.jpa.domain.Specification;

import com.vti.spring.entity.Account;
import com.vti.spring.entity.filter.AccountFilter;

public class AccountSpecification {
	
	public static Specification<Account> buildWhere(AccountFilter filter){
		
		// where username = "value"
		Specification where1 = new CustomSpecification(KeySpecification.KEY_USERNAME, filter.getUsername());
		
		// where email LIKE "%value%"
		Specification where2 = new CustomSpecification(KeySpecification.KEY_EMAIL, filter.getEmail());
		
		Specification where3 = new CustomSpecification(KeySpecification.KEY_MIN_ID, filter.getMinID());
		
		Specification where4 = new CustomSpecification(KeySpecification.KEY_MAX_ID, filter.getMaxID());
		
		Specification where5 = new CustomSpecification(KeySpecification.KEY_FULLNAME, filter.getFullName());
		
		
		// where username = value AND email LIKE "%value%"
		return Specification.where(where1).or(where2).and(where3).and(where4).or(where5);
		
	}

}
