package com.junit.junit5Application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.junit.junit5Application.model.Account;
import com.junit.junit5Application.model.Contact;
import com.junit.junit5Application.model.ContactCategory;
import com.junit.junit5Application.model.Customer;
import com.junit.junit5Application.model.Department;
import com.junit.junit5Application.model.Email;
import com.junit.junit5Application.service.AccountService;
import com.junit.junit5Application.service.ContactCategoryService;
import com.junit.junit5Application.service.CustomerService;
import com.junit.junit5Application.service.DepartmentService;
import com.junit.junit5Application.temp.Course;
import com.junit.junit5Application.temp.DepartmentServiceImpl_Temp;

@SpringBootApplication
@EnableTransactionManagement
public class Junit5Application implements CommandLineRunner {
	@Autowired
	public PlatformTransactionManager platformTransactionManager;

	@Autowired
	@Qualifier("accountServiceImplementor")
	public AccountService accountService;

	@Autowired
	@Qualifier("departmentServiceImplementor")
	public DepartmentService departmentService;

	@Autowired
	@Qualifier("customerServiceImpl")
	public CustomerService customerService;

	@Autowired
	@Qualifier("contactCategoryServiceImpl")
	public ContactCategoryService contactCategoryService;

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(Junit5Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Junit5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// performTransaction();
		// checkTransactionManagement();
		// manageCustomerInformation();
		// insertACustomer();
		tempExecution();
	}

	@Autowired
	@Qualifier("departmentServiceImpl_Temp")
	private DepartmentServiceImpl_Temp departmentServiceImpl_Temp;

	public void tempExecution() {

		Course c1 = new Course("c1");
		Course c2 = new Course("c2");
		Course c3 = new Course("c3");
		List<Course> coursesList = new ArrayList<>();
		coursesList.add(c1);
		coursesList.add(c2);
		coursesList.add(c3);

		com.junit.junit5Application.temp.Department department = new com.junit.junit5Application.temp.Department(
				"My Department temp 1", coursesList);

		departmentServiceImpl_Temp.saveDepartment(department);

	}

	public void insertACustomer() {
		logger().info("insertACustomer() execution started ....");

		ContactCategory mobileContactCategory = contactCategoryService
				.saveContactCategory(new ContactCategory("Mobile"));

		Contact myContact1 = new Email(mobileContactCategory, "luvigirodrigo6@gmail.com");
		Contact myContact2 = new Email(mobileContactCategory, "luvigirodrigo6@outlook.com");
		Contact myContact3 = new Email(mobileContactCategory, "hlprodrigo@virtusa.com");

		List<Contact> myContactList = new ArrayList<>();
		myContactList.add(myContact1);
		myContactList.add(myContact2);
		myContactList.add(myContact3);

		Customer myCustomer = new Customer("Luvigi Priyanjana Rodrigo", 26, 'M', "931630440v",
				"Kurana Katunayake, Katunayake", false, new BigDecimal(95.5), myContactList);

		Customer savedCustomer = customerService.saveCustomer(myCustomer);
		logger().info(savedCustomer.toString());
	}

//	public void manageCustomerInformation() {
//		logger().trace("Junit5Application -> manageCustomerInformation() method execution is started");
//
//		Customer customer = new Customer("Luvigi Priyanjna Rodrigo", 26, 'M', "931630440v",
//				"369/1, Samagi Mawatha, Kuarana Katunayake, Katunayake", "00940775166554", "luvigirodrigo6@gmail.com",
//				false, new BigDecimal(0.51));
//
//		try {
//			customer = customerService.saveCustomer(customer);
//			logger().info("New customer registration completed");
//			logger().info("Customer information are \n " + customer);
//
//			logger().trace("Thread is entering into sleep state for 5 seconds \n\n");
//			Thread.sleep(5000);
//
//			customer = customerService.updateCustomer(customer);
//			logger().info("Customer registration updation was completed");
//			logger().trace("Customer information are \n " + customer);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger().error(
//					"unexpected error occured during the execution of Junit5Application -> manageCustomerInformation()");
//		}
//
//		logger().trace("Junit5Application -> manageCustomerInformation() method execution is completed");
//	}

	public void checkTransactionManagement() {
		Department dep = new Department();
		dep.setName("IT Department");
		departmentService.saveDepartment(dep);
	}

	public void performTransaction() {
		try {
			List<Account> accountsList = new ArrayList<Account>();

			Account myAccount = new Account("Christiano Ronaldo", 10000);
			Account herAccount = new Account("Lional Messi", 20000);

			Account shaniAccount = new Account("Kumar Sangakkara", 1000);
			Account namalAccount = new Account("Sanath Jayasooriya", 10000);

			accountsList.add(myAccount);
			accountsList.add(herAccount);
			accountsList.add(shaniAccount);
			accountsList.add(namalAccount);

			accountService.saveAllAccounts(accountsList);
			System.out.println("Account information successfully saved");
			accountsList.forEach(System.out::println);

			accountService.transferCredit(herAccount.getAccoutId(), myAccount.getAccoutId(), 5000);

			/*
			 * Should be throwing an exception if shaniAccountBalance is less than the
			 * amount transfered
			 */
			// accountService.transferCredit(shaniAccount.getAccoutId(),
			// namalAccount.getAccoutId(), 45000);

		} catch (Exception e) {
			System.out.println("UNEXPECTED EXCEPTION OCCURED");
			e.printStackTrace();
		}
	}
}