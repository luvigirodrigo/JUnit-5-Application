package com.junit.junit5Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junit.junit5Application.model.ContactCategory;

@Repository("contactCategoryRepository")
public interface ContactCategoryRepository extends JpaRepository<ContactCategory, Integer> {

}
