package com.junit.junit5Application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.junit5Application.Junit5Application;
import com.junit.junit5Application.model.Account;
import com.junit.junit5Application.repository.AccountRepository;
import com.junit.junit5Application.serviceimpl.AccountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Junit5Application.class })
@TestInstance(Lifecycle.PER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("When running AccountServiceImplTest")
class AccountServiceImplTest {

	@Autowired
	@Qualifier("accountRepository")
	public AccountRepository accountRepository;

	@Autowired
	@Qualifier("accountServiceImplementor")
	public AccountServiceImpl accountServiceImpl;

	public Account account;

	@Test
	@DisplayName("When saving a new account")
	public void testSaveAccount() {
		Account account = new Account("TEST_ACCOUNT_HOLDER", 500);
		assertEquals(account, accountServiceImpl.saveAccount(account));
	}
}