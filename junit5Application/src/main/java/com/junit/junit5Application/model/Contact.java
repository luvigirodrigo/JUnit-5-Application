package com.junit.junit5Application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "contact")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private int contactId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "description", nullable = true, length = 250)
	private String description;

	@ManyToOne
	@JoinColumn(name = "contact_category_id")
	private ContactCategory contactCategory;

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContactCategory getContactCategory() {
		return contactCategory;
	}

	public void setContactCategory(ContactCategory contactCategory) {
		this.contactCategory = contactCategory;
	}

	public Contact() {
		super();
	}

	public Contact(int contactId) {
		super();
		this.contactId = contactId;
	}

	public Contact(ContactCategory contactCategory) {
		super();
		this.contactCategory = contactCategory;
	}

	public Contact(Customer customer, ContactCategory contactCategory) {
		super();
		this.customer = customer;
		this.contactCategory = contactCategory;
	}

	public Contact(Customer customer, String description, ContactCategory contactCategory) {
		super();
		this.customer = customer;
		this.description = description;
		this.contactCategory = contactCategory;
	}

	public Contact(int contactId, Customer customer, String description, ContactCategory contactCategory) {
		super();
		this.contactId = contactId;
		this.customer = customer;
		this.description = description;
		this.contactCategory = contactCategory;
	}

}
