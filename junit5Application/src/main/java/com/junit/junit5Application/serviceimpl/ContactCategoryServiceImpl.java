package com.junit.junit5Application.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.junit.junit5Application.model.ContactCategory;
import com.junit.junit5Application.repository.ContactCategoryRepository;
import com.junit.junit5Application.service.ContactCategoryService;

@Service("contactCategoryServiceImpl")
public class ContactCategoryServiceImpl implements ContactCategoryService {

	@Autowired
	@Qualifier("contactCategoryRepository")
	private ContactCategoryRepository contactCategoryRepository;

	@Override
	public ContactCategory saveContactCategory(ContactCategory contactCategory) {
		return contactCategoryRepository.save(contactCategory);
	}

	@Override
	public ContactCategory updateContactCategory(ContactCategory contactCategory) {
		return contactCategoryRepository.save(contactCategory);
	}

	@Override
	public List<ContactCategory> searchAllContactCategories() {
		return contactCategoryRepository.findAll();
	}

}
