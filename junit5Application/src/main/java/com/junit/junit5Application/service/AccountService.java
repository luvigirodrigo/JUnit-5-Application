package com.junit.junit5Application.service;

import java.util.List;

import com.junit.junit5Application.model.Account;

public interface AccountService {
	public Account saveAccount(Account account);
	
	public List<Account> saveAllAccounts(List<Account> accountsList);

	public Account searchAccountByAccountId(Integer accountId);
	
	public boolean transferCredit(Integer cashSender, Integer cashReceiver, double amountTransfered);
}