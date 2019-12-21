package com.junit.junit5Application.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "customer_name", length = 50, nullable = false)
	private String customerName;

	@Column(name = "age", length = 3, nullable = false)
	private int age;

	@Column(name = "gender", length = 1, nullable = false)
	private char gender;

	@Column(name = "nic", nullable = false)
	private String NIC;

	@Column(name = "permanent_address")
	private String permanentAddress;

	@Column(name = "is_married", nullable = false)
	private boolean isMarried;

	@Column(name = "rating")
	private BigDecimal rating;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Contact> contactList;

	@CreationTimestamp
	@Column(name = "registered_timestamp", nullable = false, updatable = false)
	private Timestamp registeredTimeStamp;

	@UpdateTimestamp
	@Column(name = "updated_timestamp", nullable = false)
	private Timestamp updatedTimeStamp;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public Timestamp getRegisteredTimeStamp() {
		return registeredTimeStamp;
	}

	public void setRegisteredTimeStamp(Timestamp registeredTimeStamp) {
		this.registeredTimeStamp = registeredTimeStamp;
	}

	public Timestamp getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Timestamp updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public Customer() {
		super();
	}

	public Customer(String customerName, int age, char gender, String nIC, String permanentAddress, boolean isMarried,
			BigDecimal rating, List<Contact> contactList, Timestamp registeredTimeStamp, Timestamp updatedTimeStamp) {
		super();
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		NIC = nIC;
		this.permanentAddress = permanentAddress;
		this.isMarried = isMarried;
		this.rating = rating;
		this.contactList = contactList;
		this.registeredTimeStamp = registeredTimeStamp;
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public Customer(String customerName, int age, char gender, String nIC, String permanentAddress, boolean isMarried,
			BigDecimal rating) {
		super();
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		NIC = nIC;
		this.permanentAddress = permanentAddress;
		this.isMarried = isMarried;
		this.rating = rating;
	}

	public Customer(String customerName, int age, char gender, String nIC, String permanentAddress, boolean isMarried,
			BigDecimal rating, List<Contact> contactList) {
		super();
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		NIC = nIC;
		this.permanentAddress = permanentAddress;
		this.isMarried = isMarried;
		this.rating = rating;
		this.contactList = contactList;
	}

	public Customer(int customerId, String customerName, int age, char gender, String nIC, String permanentAddress,
			boolean isMarried, BigDecimal rating, List<Contact> contactList, Timestamp registeredTimeStamp,
			Timestamp updatedTimeStamp) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.age = age;
		this.gender = gender;
		NIC = nIC;
		this.permanentAddress = permanentAddress;
		this.isMarried = isMarried;
		this.rating = rating;
		this.contactList = contactList;
		this.registeredTimeStamp = registeredTimeStamp;
		this.updatedTimeStamp = updatedTimeStamp;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age + ", gender="
				+ gender + ", NIC=" + NIC + ", permanentAddress=" + permanentAddress + ", isMarried=" + isMarried
				+ ", rating=" + rating + ", contactList=" + contactList + ", registeredTimeStamp=" + registeredTimeStamp
				+ ", updatedTimeStamp=" + updatedTimeStamp + "]";
	}

}
