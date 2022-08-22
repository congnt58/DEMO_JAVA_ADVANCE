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
		
		//where id >= value
		Specification where3 = new CustomSpecification(KeySpecification.KEY_MIN_ID, filter.getMinID());
		
		//where id <= value
		Specification where4 = new CustomSpecification(KeySpecification.KEY_MAX_ID, filter.getMaxID());
		
		//where fullname = value
		Specification where5 = new CustomSpecification(KeySpecification.KEY_FULLNAME, filter.getFullName());
		
		//where departmentName = value
		Specification where6 = new CustomSpecification(KeySpecification.KEY_DP_NAME, filter.getDerpatmentName());
		
		// where username = value AND email LIKE "%value%"
		return Specification.where(where1).or(where2).and(where3).and(where4).or(where5).and(where6);
		
	}

}
