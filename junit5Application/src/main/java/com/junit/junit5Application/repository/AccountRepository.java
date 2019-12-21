package com.junit.junit5Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junit.junit5Application.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByAccountId(Integer accountId);
}