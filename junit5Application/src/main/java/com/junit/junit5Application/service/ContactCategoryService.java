package com.junit.junit5Application.service;

import java.util.List;

import com.junit.junit5Application.model.ContactCategory;

public interface ContactCategoryService {
	public ContactCategory saveContactCategory(ContactCategory contactCategory);

	public ContactCategory updateContactCategory(ContactCategory contactCategory);

	public List<ContactCategory> searchAllContactCategories();
}
