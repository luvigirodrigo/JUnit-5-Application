package com.junit.junit5Application.util;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Duration;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.junit5Application.util.MathUtils;

@RunWith(SpringRunner.class)//Required for @EnabledIf ,  @DisabledIf (Spring specific)
@SpringBootTest//Required for @EnabledIf ,  @DisabledIf (Spring specific)
@SpringJUnitConfig(MathUtilsTest.Config.class)//Required for @EnabledIf ,  @DisabledIf (Spring specific)
/* 
 	@TestInstance(TestInstance.Lifecycle.PER_METHOD)
   	By default test instance creation behavior - Separate instance for each method 
   	(No need to specify explicitly)
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Creating only one test class instance for every method
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Defining the order of test case executions
@DisplayName("When running MathUtilsTest, ")
class MathUtilsTest {
	
	@Configuration
	public static class Config {} //Required for @EnabledIf ,  @DisabledIf (Spring specific)
	
	private MathUtils mathUtils;

	/*
		static modifier is not mandatory for @BeforeAll , @AfterAll annotated methods
		if the class has been annotated with @TestInstance(TestInstance.Lifecycle.PER_CLASS) 
		as if an instance is being created just once for the whole class. Because only one instance 
		is created for each method.Multiple instances are not created
	*/
	@BeforeAll
	public void beforeAllInit() {
		System.out.println("This needs to run before all. Even before the test class instance is initialized");
	}
	
	/*TestInfo and TestReporter*/
	/*===================================================================================================================================================*/

	/*
	 * TestInfo 	- Contains information about the tests
	 * TestReporter	- Let you access the logs/console where JUnit is reporting errors 
	 * 				  Used if we want to report something out
	 * 				  (Possible to add customized messages also to final JUnit report)
	 * 
	 * Both of these interfaces are instances which are maintained by JUnit. We can use
	 * dependency injection to provide implementation for them.
	 * 
	 * We can use @BeforeEach to tell JUnit to provide these values
	*/ 
	
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeEach
	public void init(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("Initializing before each");
		mathUtils = new MathUtils();
		
		//Assigning values for instance variables
		this.testInfo=testInfo;
		this.testReporter=testReporter;
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
		
	}
	
	@AfterEach
	public void cleanUp() {
		System.out.println("Cleaning up ......");
	}
	
	@AfterAll
	public static void afterAllCleanUp() {
		System.out.println("This should run after all method executions");
	}
	
	/*
	 * @Test - Marks a method as a test - Informs the JUnit engine what methods need
	 * to be run
	 */
	@Test
	public void testAdd() {
		int expectedValue = 2;
		int actualValue = mathUtils.add(1, 1);

		// Check JUnit 5 API documentation for more
		assertEquals(expectedValue, actualValue, "The add method should add two numbers ");
	}

	@Test
	/*
		@DisplayName("String value") , this String text will be displayed 
	 	instead of the method name in JUnit GUI. Easy to understand 
	*/
	@DisplayName("Calculating the area of a circle by providing the radius")
	public void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return the right circle area");
	}
	
	@Test
	public void testDivide() {
		assertThrows(ArithmeticException.class,() ->
				mathUtils.divide(1, 0),"Divide by zero shold throw an ArithmeticException");
	}
	
	@Test
	@Disabled // @Disabled annotated methods will be ignored/skipped while test case execution by JUnit
	// This @DisplayName text should not be displayed in JUnit GUI as this method is disabled
	@DisplayName("TODO method. Should not run")
	public void disabledMethod() {
		fail("TODO method, should fail if executed");
	}
	
	/*CONDITIONAL TEST EXECUTION*/
	/*===================================================================================================================================================*/
	
	//1. Operating System Conditions----------------------------------------------------------------------------------------------------------------------
	/*
	 * Sometimes, we need to change our test scenarios depending on the operating
	 * systems (OS) they’re running on. In these cases, the @EnabledOnOs annotation
	 * comes in handy.
	 * 
	 * The usage of @EnabledOnOs is simple – we just need to give it a value for the
	 * OS type. Furthermore, it also accepts an array argument for when we want to
	 * target multiple operating systems
	 */
	
	@Test
	@EnabledOnOs(OS.WINDOWS) //Excecuted within an specified operating system or else test case is ignored/skipped
	public void testOnWindowsSpecificOS() {
		System.out.println("Windows OS specific test case ran");
	}
	
	@Test
	@EnabledOnOs(OS.LINUX) // This test only runs on Linux operating systems
	@DisabledOnOs(OS.MAC) // Test case is disabled if the OS is Mac
	public void testOnLinuxSpecificOS() {
		System.out.println("Linux OS specific test case ran");
	}
	
	@Test
	@DisplayName("Should run on both Windows and MAC")
	@EnabledOnOs({ OS.WINDOWS, OS.MAC })
	public void shouldRunBothWindowsAndMac() {
		System.out.println("Should run on both Windows and MAC");
	}
	
	//2. Java Runtime Environment Conditions ------------------------------------------------------------------------------------------------------------
	/*
	 * We can also target our tests to run on specific JRE versions using
	 * the @EnableOnJre and @DisableOnJre annotations. These annotations also accept
	 * an array to enable or disable multiple Java versions:
	 */
	
	@Test
	@EnabledOnJre(JRE.JAVA_10) // Enables the test case on a specific JRE only
	@DisabledOnJre(JRE.JAVA_11) // Test case is disabled if the JRE version is 11
	public void testRunOnJRE_10() {
		System.out.println("Executed only on JRE- Java 10");
	}

	@Test
	@EnabledOnJre(JRE.JAVA_8) // Enables the test case on a specific JRE only
	public void testRunOnJRE_8() {
		System.out.println("Executed only on JRE- Java 8");
	}
	
	@Test
	@DisabledOnJre(JRE.OTHER)
	@DisplayName("Runs only on up to date JREs")
	public void thisTestOnlyRunsWithUpToDateJREs() {
		System.out.println("this test will only run on Java 8, 9, 10, and 11.");
	}

	
	//3. System property conditions  -------------------------------------------------------------------------------------------------------------------
	/*
	 * Now, if we want to enable our tests based on JVM system properties, we can
	 * use the @EnabledIfSystemProperty annotation.
	 * 
	 * To use it, we must provide named and matches arguments. The named argument is
	 * used to specify an exact system property. The matches is used for defining
	 * the pattern of property value with a regular expression.
	 * 
	 * For instance, let’s say we want to enable a test to run only when the virtual
	 * machine vendor name starts with “Oracle”
	 */
	@Test
	@EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Oracle.*")
	@DisplayName("@EnabledIfSystemProperty - onlyIfVendorNameStartsWithOracle")
	public void onlyIfVendorNameStartsWithOracle() {
		System.out.println("\"@EnabledIfSystemProperty annotated method\"");
	}
	
	/*
	 * Likewise, we have the @DisabledIfSystemProperty to disable tests based on JVM
	 * system properties. To demonstrate this annotation, let’s take a look at an
	 * example:
	 */
	@Test
	@DisplayName("@DisabledIfSystemProperty - disabledIfFileSeperatorIsSlash")
	@DisabledIfSystemProperty(named = "file.separator", matches = "[/]")
	public void disabledIfFileSeperatorIsSlash() {
		System.out.println("\"@DisabledIfSystemProperty annotated method\"");
	}
	
	
	//4. Environment Variables conditions --------------------------------------------------------------------------------------------------------------
	/*
	 * We can also specify environment variable conditions for our tests
	 * with @EnabledIfEnvironmentVariable and @DisabledIfEnvironmentVariable
	 * annotations.
	 * 
	 * And, just like the annotations for system property conditions, these
	 * annotations take two arguments — named and matches — for specifying the
	 * environment variable name and regular expression to match against environment
	 * variable values:
	 */

	@Test
	@DisplayName("@EnabledIfEnvironmentVariable - onlyRunOnUbuntuServer")
	@EnabledIfEnvironmentVariable(named = "GDMSESSION", matches = "ubuntu")
	public void onlyRunOnUbuntuServer() {
		System.out.println("Runs on Ubintu server");
	}

	@Test
	@DisplayName("@DisabledIfEnvironmentVariable - shouldNotRunWhenTimeIsNotUTF8")
	@DisabledIfEnvironmentVariable(named = "LC_TIME", matches = ".*UTF-8.")
	public void shouldNotRunWhenTimeIsNotUTF8() {
		System.out.println("should not run when time is not UTF8");
	}
	
	//5. Script based conditions -----------------------------------------------------------------------------------------------------------------------
	/*
	 * Additionally, JUnit 5 Jupiter gives us the ability to decide our test’s
	 * running conditions by writing scripts within @EnabledIf and @DisabledIf
	 * annotations.
	 * 
	 * These annotations accept three arguments:
	 * 
	 * value 				– contains the actual script to run. 
	 * engine (optional) 	– specifies the scripting engine to use; the default is Oracle Nashorn. 
	 * reason (optional) 	– for logging purposes, specifies the message JUnit should print if our test fails. 
	 * 
	 * So, let’s see a simple example where we specify only a one-line script, without additional arguments on the annotation:
	 */

	@Test
	@EnabledIf("'FR' == systemProperty.get('user.country')")
	@DisplayName("Only french people will run this method")
	public void onlyFrenchPeopleWillRunThisMethod() {
		System.out.println("@EnabledIf(\"'FR' == systemProperty.get('user.country')\") - onlyFrenchPeopleWillRunThisMethod");
	}
	
	//Also, the usage of @DisabledIf is exactly the same:
	@Test
	@DisabledIf("java.lang.System.getProperty('os.name').toLowerCase().contains('mac')")
	@DisplayName("Should not fun on MacOs")
	public void shouldNotRunOnMacOS() {
	    System.out.println("Should not run on MacOs");
	}
	
	/*
	 * Furthermore, we can write multi-line scripts with the value argument.
	 * Let’s write a brief example to check the month name before running the test.
	 * 
	 * We’ll define a sentence for reason with supported placeholders:
	 * 
	 * {annotation} – the string for representing annotation instance. 
	 * {script} 	– the script text that evaluated inside value argument. 
	 * {result} 	– the string for representing the return value of the evaluated script. 
	 * 
	 * For this instance, we will have a multi-line script in the value argument and values for engine
	 * and reason:
	 */
	@Test
	@EnabledIf(
		value = {
				
		    "load('nashorn:mozilla_compat.js')",
		    "importPackage(java.time)",
		    "",
		    "var thisMonth = LocalDate.now().getMonth().name()",
		    "var february = Month.FEBRUARY.name()",
		    "thisMonth.equals(february)"
		},
	    engine = "nashorn",
	    reason = "On {annotation}, with script: {script}, result is: {result}"
	)
	@DisplayName("This method only runs on specified month")
	public void onlyRunsInSpecifiedMonth() {
		System.out.println("This method only runs on specified month within the script");
	}
	
	/*
	 * We can use several script bindings when writing our scripts:
	 * 
	 * systemEnvironment – to access system environment variables. 
	 * systemProperty – to access system property variables. 
	 * junitConfigurationParameter – to access configuration parameters. 
	 * junitDisplayName – test or container display name.
	 * junitTags – to access tags on test or container. 
	 * anotherUniqueId – to get the unique id of test or container. 
	 * 
	 * Finally, let’s look at another example to see how to use scripts with bindings:
	 */
	@Test
	@DisabledIf("systemEnvironment.get('XPC_SERVICE_NAME') != null" +
	        "&& systemEnvironment.get('XPC_SERVICE_NAME').contains('intellij')")
	@DisplayName("This will not run on IntelliJ")
	public void notValidForIntelliJ() {
		System.out.println("This will not run on IntelliJ. Not valid for IntelliJ");
	}
	
	//More testing with @EnabledIf & DisabledIf Annotations plus Spring extension support
	
	@Test
	@DisplayName("JUnit 5 Specific - @EnabledIf(\"true\") annotated method")
	// @EnabledIf with a text literal true
	@EnabledIf("true")
	public void testEnabledIfAnnotatedMethod() {
		System.out.println("JUnit 5 specific @EnabledIf(\"true\")");
	}

	private final String STATUS = "true";
	
	@Test
	@DisplayName("JUnit5 specific- @EnabledIf with a constant String value")
	@EnabledIf(STATUS) // STATUS must be final (should be a constant)
	public void testEnabledWithStringValue() {
		System.out.println("JUnit5 specific- @EnabledIf with a constant String value");
	}

	// JUnit 5 tests using the SpringExtension
	@Test
	@DisplayName("Spring Specific - @EnabledIf(\"true\") annotated method")
	// @EnabledIf with a Property Placeholder
	@org.springframework.test.context.junit.jupiter.EnabledIf(expression = "${custom.junit5.test.enabled}", loadContext = true)
	public void testCheckFromAppLicationProperties() {
		System.out.println("Spring specific @EnabledIf annotated method");
	}

	// JUnit 5 tests using the SpringExtension
	@Test
	@DisplayName("Spring Specific - @DisabledIf(\"true\") annotated method - Should not run")
	// @DisabledIf with a Property Placeholder
	@org.springframework.test.context.junit.jupiter.DisabledIf(expression = "${custom.junit5.disable_some}", loadContext = true)
	public void testCheckToDisableFromAppLicationProperties() {
		System.out.println("Spring specific @DisabledIf annotated method");
	}

	// JUnit 5 tests using the SpringExtension
	@Test
	// @EnabledIf with Spring Expression Language (SpEL) expressions
	@org.springframework.test.context.junit.jupiter.EnabledIf("#{systemProperties['java.version'].startsWith('1.8')}")
	@DisplayName("Accessing System properties with Spring specific @EnabledIf")
	public void testEnableOnJavaVertion() {
		System.out.println("Spring framework specific @EnabledIf - testEnableOnJavaVertion");
	}
	
	//6. Creating Custom Conditional Annotations ------------------------------------------------------------------------------------------------------
	/*
	 * A very powerful feature that comes with JUnit 5 is the ability to create
	 * custom annotations. We can define custom conditional annotations by using a
	 * combination of existing conditional annotations.
	 * 
	 * For instance, suppose we want to define all our tests to run for specific OS
	 * types with specific JRE versions. We can write a custom annotation for this:
	 */
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@Test
	@DisabledOnOs({ OS.WINDOWS, OS.SOLARIS, OS.OTHER })
	@EnabledOnJre({ JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11 })
	// Defining the custom annotation name
	@interface ThisTestWillOnlyRunAtLinuxAndMacWithJava9Or10Or11 {
	}
	 
	@Test // Here @Test is not mandatory
	@ThisTestWillOnlyRunAtLinuxAndMacWithJava9Or10Or11
	@DisplayName("This test will only run at Linux and MacOS with Java version 9 ,10 or 11")
	public void someSuperTestMethodHere() {
		// this method will run with Java9, 10, 11 and Linux or macOS.
		System.out.println("This test will only run at Linux and MacOS with Java version 9 ,10 or 11");
	}
	
	/*Furthermore, we can use script-based annotations to create a custom annotation:*/
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@DisabledIf("Math.random() >= 0.5")
	@interface CoinToss {
	}
	 
	@RepeatedTest(2)
	@CoinToss
	@DisplayName("Tossing the coin")
	public void gamble() {
		// this method run roughly 50% of the time
		System.out.println("Tossing the Coin");
	}
	
	
	/* ASSUMPTIONS */
	/*===================================================================================================================================================*/
	
	//Assumptions
	@Test
	@DisplayName("Assumptions test method")
	public void testAssumption() {
		boolean isServerUp=true;
		//If the assumption fails , then the test will not run/ fail as it does not make sense to run the test after failing the assumption
		assumeTrue(isServerUp);
		assertEquals(2, mathUtils.add(1, 1));
	}	
	
	
	/* NESTED TESTS (ORGANIZING/GROUPING TESTS) */
	/*===================================================================================================================================================*/

	/*
	 * @Nested example - Can be used to group bunch of test cases 
	 * 
	 * This creates a single root node in JUnit screen as AddTest or 
	 * whatever defined in @DisplayName and under that grouped/organized 
	 * test cases will be displayed in the hierarchy
	 * 
	 * In case of failure of any grouped method. The root node will be displayed
	 * as failed
	 */
	@Nested
	@DisplayName("@Nested AddTest")
	class AddTest{
		@Test
		@DisplayName("When adding two possitive numbers")
		public void testAddPositiveNumbers() {
			assertEquals(5, mathUtils.add(3, 2),"Should return the right sum");
		}
		
		@Test
		@DisplayName("When adding two negative numbers")
		public void testAddNegativeNumbers() {
			assertEquals(-5, mathUtils.add(-3, -2),"Should return the right sum");
		}
	}//end of AddTest class
	
	// assertAll() example
	@Test
	@DisplayName("assertAll Example")
	public void assertAllExample() {
		assertAll(
				() -> assertEquals(5, mathUtils.add(3, 2)), 
				() -> assertEquals(6, mathUtils.multiply(3, 2)),
				() -> assertEquals(5, mathUtils.divide(10, 2))
		);
	}

	/* FAILING A TEST */
	/*===================================================================================================================================================*/

	@Disabled
	@Test
	public void testFailExecution() {
		fail("Immediately fails the test");// Immediately fails a test case
	}
	
	
	/* LAZY ASSERT MESSAGES */
	/*===================================================================================================================================================*/
	
	@Test
	@DisplayName("Lazy assert messages")
	public void testAddLazyAssertMessages() {
		int expectedValue = 10;
		int actualValue = mathUtils.add(8, 2);
		
		/*
		 * assertEquals() method receives the three parameters passed into it.
		 * Therefore, Message String is also obtained. Building this is done
		 * irrespective of whether the test passes or fails. If this String message is
		 * expensive (more memory and processing power is required) to calculate we can
		 * calculate it lazyly. To do that we can create a lambda and pass it over
		 * (Lambda is basically a supplier which returns a single value). It will be
		 * executed only if the test fails. Which means that the string is not
		 * constructed until the test fails
		 * 
		 * To do this all we need to do is converting this string to a lambda
		 */
		
		assertEquals(
				expectedValue, 
				actualValue,
				() -> "Should return the correct sum : " + expectedValue + " but returned : " + actualValue
		);
	}
	
	/* REPEATED TESTS */
	/*===================================================================================================================================================*/
	
	/*
	 * Displays an expandable node in JUnit output screen with test name followed
	 * by repetitions in a hierarchy
	 * 
	 * Main node indicates successful if all the repetitions are passed
	 * 
	 * To get hold of the repetitions (get control of the repetitions) we should 
	 * pass the argument RepetitionInfo
	 */
	@RepeatedTest(10) // is used instead of @Test annotation
	@DisplayName("Repeated test method - @RepeatedTest annotated")
	public void testRepeatedTest(RepetitionInfo repetitionInfo) {
		
		//RepetitionInfo object contains information about each repetition
		
		System.out.println("Total Number Of Repetitions : "+ repetitionInfo.getTotalRepetitions());
		System.out.println("Current Repetition Is : "+ repetitionInfo.getCurrentRepetition());
		System.out.println("RepetitionInfo toString : "+ repetitionInfo.toString());
		
		System.out.println("This test is repeated number of times");
	}
	
	/* TAGGING TESTS */
	/*===================================================================================================================================================*/
	
	/*
	 * In test configuration we can include tags to be run (whatever the tags we
	 * have specified for test cases)
	 * 
	 * So only the test cases with configured tags will run 
	 * (selectively run the tests depending on specified tags) 
	 * (Run -> RunConfigurations -> Configure -> Specify Include and Exclude Tags)
	 * 
	 * (maven-surefire-plugin also has an include and exclude configuration
	 * (refer the documentation <configuration> </configuration>))
	 */

	@Test
	@DisplayName("Tagged test case 1 ")
	@Tag("important")
	public void testTaggedTest1() {
		System.out.println("important test case 1");
	}

	@Test
	@DisplayName("Tagged test case 2 ")
	@Tag("important")
	public void testTaggedTest2() {
		System.out.println("important test case 2");
	}

	@Test
	@DisplayName("Tagged test case 2")
	@Tag("secondImportant")
	public void testAnotherTaggedTest() {
		System.out.println("secondImportant test case");
	}	
	
	
	//Using TestInfo and TestReporter ------------------------------------------------------------------------------------------------------------------
	
	@Test
	@DisplayName("TestInfo sample method")
	@Tag("important")
	public void testTestInfoFunctionalities() {

		//TestInfo Related method executions
		//These information will be displayed on Console output
		System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
		System.out.println();
		System.out.println("Test info getClass : " + testInfo.getClass());
		System.out.println("Test info - DisplayName : " + testInfo.getDisplayName());
		System.out.println("Test info - Tags : " + testInfo.getTags());
		System.out.println("Test info - Test Class : " + testInfo.getTestClass());
		System.out.println("Test info - Test Method : " + testInfo.getTestMethod());
		System.out.println("Test info - toString : " + testInfo.toString());
		
		//TestReporter related method executions
		/* 
		 * These information will be displayed on JUnit output (Whatever the console
		 *  to which that JUnit is printing outputs to)
		 *  
		 *  Example output
		 *  ReportEntry [timestamp = 2019-04-21T18:47:16.290866700, 
		 *  			value = 'Running TestInfo sample method with tags [important]']
		 *  
		 *  Provides a recommended way of logging test messages to the console
		 *  ** Find out more about custom providers related to TestReporter
		*/
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}
	
	/* TIMEOUT TESTING */
	/*===================================================================================================================================================*/
	@Test
	@DisplayName("Timeout check - JUnit5")
	public void testTimeOutCheck() {
		assertTimeout(Duration.ofMillis(2000), () -> holdProcess(1900),
				"Method should run within " + Duration.ofMillis(2000) + " milliseconds");
	}

	private void holdProcess(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.out.println("Thread got interrupted");
		}
	}

}