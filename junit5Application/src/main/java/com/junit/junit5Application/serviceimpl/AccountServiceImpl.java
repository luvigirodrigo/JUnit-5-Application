package com.junit.junit5Application.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.junit.junit5Application.exception.InsufficientAccountBalanceException;
import com.junit.junit5Application.exception.InvalidAccountException;
import com.junit.junit5Application.model.Account;
import com.junit.junit5Application.repository.AccountRepository;
import com.junit.junit5Application.service.AccountService;

@Service("accountServiceImplementor")
public class AccountServiceImpl implements AccountService {

	@Autowired
	@Qualifier("accountRepository")
	public AccountRepository accountRepository;

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public List<Account> saveAllAccounts(List<Account> accountsList) {
		return accountRepository.saveAll(accountsList);
	}

	@Override
	public boolean transferCredit(Integer cashSenderAccountId, Integer cashReceiverAccountId, double amountTransfered) {
		if (isValidAccount(cashSenderAccountId)) {
			if (isValidAccount(cashReceiverAccountId)) {
				if (hasSufficientBalanceMoreThan(amountTransfered, cashSenderAccountId)) {
					Account cashSenderAccount = accountRepository.findByAccountId(cashSenderAccountId);
					Account cashReceiverAccount = accountRepository.findByAccountId(cashReceiverAccountId);

					cashSenderAccount.setBalance((cashSenderAccount.getBalance()) - amountTransfered);
					cashReceiverAccount.setBalance((cashReceiverAccount.getBalance()) + amountTransfered);

					List<Account> accountsToBeUpdated = new ArrayList<Account>();
					accountsToBeUpdated.add(cashSenderAccount);
					accountsToBeUpdated.add(cashReceiverAccount);

					accountRepository.saveAll(accountsToBeUpdated);
					return true;

				} else {
					throw new InsufficientAccountBalanceException("Insufficient account balance. Account no : "
							+ cashSenderAccountId + " does not have a sufficient balance for completing the transfer");
				}
			} else {
				throw new InvalidAccountException("Invalid cash receiver account. Please check account information");
			}
		} else {
			throw new InvalidAccountException("Invalid cash sender account. Please check account information");
		}
	}

	private boolean isValidAccount(Integer accountId) {
		return (accountRepository.findByAccountId(accountId) != null) ? true : false;
	}

	private boolean hasSufficientBalanceMoreThan(double value, Integer accountId) {
		if (isValidAccount(accountId)) {
			if (accountRepository.findByAccountId(accountId).getBalance() >= value) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Account searchAccountByAccountId(Integer accountId) {
		return accountRepository.findByAccountId(accountId);
	}
}
