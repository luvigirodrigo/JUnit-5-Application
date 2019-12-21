package com.junit.junit5Application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Email extends Contact {

	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Email() {
		super();
	}

	public Email(ContactCategory contactCategory, String emailAddress) {
		super(contactCategory);
		this.emailAddress = emailAddress;
	}

	public Email(Customer customer, ContactCategory contactCategory, String emailAddress) {
		super(customer, contactCategory);
		this.emailAddress = emailAddress;
	}
}
