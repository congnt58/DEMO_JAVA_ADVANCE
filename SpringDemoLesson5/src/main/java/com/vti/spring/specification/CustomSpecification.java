package com.vti.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.spring.entity.Account;

public class CustomSpecification implements Specification<Account> {

	private String key;

	private Object value;

	public CustomSpecification(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (value == null) {
			return null;
		}

		switch (key) {
		case KeySpecification.KEY_USERNAME:
			// where username = "value"
			return criteriaBuilder.equal(root.get("username"), value.toString());
		case KeySpecification.KEY_EMAIL:
			// where email Like '%value%'
			return criteriaBuilder.like(root.get("email"), "%" + value.toString() + "%");
		case KeySpecification.KEY_MIN_ID:
			// where id >= value
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
		case KeySpecification.KEY_MAX_ID:
			// where id <= value
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
		case KeySpecification.KEY_FULLNAME:
			return criteriaBuilder.equal(root.get("fullName"), value.toString());
		default:
			break;
		}

		return null;
	}

}
