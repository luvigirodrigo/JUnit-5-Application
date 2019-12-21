package com.junit.junit5Application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id", length = 11)
	private Integer accountId;

	@Column(name = "account_holder_name", length = 20)
	@NotNull
	private String accountHolderName;

	@Column(name = "balance")
	@NotNull
	private double balance;

	public Integer getAccoutId() {
		return accountId;
	}

	public void setAccoutId(Integer accoutId) {
		this.accountId = accoutId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account() {
		super();
	}

	public Account(@NotNull String accountHolderName, @NotNull double balance) {
		super();
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}

	public Account(Integer accoutId) {
		super();
		this.accountId = accoutId;
	}

	public Account(Integer accoutId, @NotNull String accountHolderName, @NotNull double balance) {
		super();
		this.accountId = accoutId;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accoutId=" + accountId + ", accountHolderName=" + accountHolderName + ", balance=" + balance
				+ "]";
	}
}
