package com.junit.junit5Application.model;

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

@Entity
@Table(name = "contact_category")
public class ContactCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_category_id")
	private int contactCategoryId;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@Column(name = "contact_category_description", nullable = true, length = 250)
	private String contactCategoryDescription;

	@Column(name = "category_image", nullable = true)
	private String categoryImage;

	@OneToMany(mappedBy = "contactCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Contact> contactList;

	public int getContactCategoryId() {
		return contactCategoryId;
	}

	public void setContactCategoryId(int contactCategoryId) {
		this.contactCategoryId = contactCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getContactCategoryDescription() {
		return contactCategoryDescription;
	}

	public void setContactCategoryDescription(String contactCategoryDescription) {
		this.contactCategoryDescription = contactCategoryDescription;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public ContactCategory() {
	}

	public ContactCategory(int contactCategoryId) {
		super();
		this.contactCategoryId = contactCategoryId;
	}

	public ContactCategory(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public ContactCategory(String categoryName, String contactCategoryDescription, String categoryImage,
			List<Contact> contactList) {
		super();
		this.categoryName = categoryName;
		this.contactCategoryDescription = contactCategoryDescription;
		this.categoryImage = categoryImage;
		this.contactList = contactList;
	}

	public ContactCategory(int contactCategoryId, String categoryName, String contactCategoryDescription,
			String categoryImage, List<Contact> contactList) {
		super();
		this.contactCategoryId = contactCategoryId;
		this.categoryName = categoryName;
		this.contactCategoryDescription = contactCategoryDescription;
		this.categoryImage = categoryImage;
		this.contactList = contactList;
	}
}
