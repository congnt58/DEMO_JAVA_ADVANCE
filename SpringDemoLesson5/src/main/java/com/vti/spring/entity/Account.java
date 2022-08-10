package com.vti.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`Account`")
public class Account {

	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email")
	private String email;

	@Column(name = "UserName")
	private String username;

	@Column(name = "Fullname")
	private String fullName;
	
	@Column(name = "password")
	private String password;

	@Transient
	private Department department;

	public Account(int id) {
		this.id = id;
	}

}
