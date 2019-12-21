package com.junit.junit5Application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
	/*
	 Logging 
	 ---------------------------------------------------------------------------------------------------------------------------
	 > Advantages of a proper logging framework :
	 	* Provides the benefit of allowing us to configure the behavior rather than do things one way (Configurable)
	 	* We can decide where the messages go,
	 	* We can set things like logging levels,
	 	* Ability to set log destination (to a file,email,database) 
	 
	 > Spring boot comes with its own logging framework included

	 > Basic way to add logging is to use the class called LoggerFactory and it has
	   a static method called getLogger(<Class>.class)
	  
	 > Dependencies which are causing logging functionality to be available in the project
		 (Spring Commons Logging Bridge)
		 			spring-jcl	  <-- spring-boot-starter-logging <-- spring-boot-starter-web
	  
	 > slf4j is pretty much an interface. The actual implementation turns out to be default implementation in spring boot.
	    When we use starter dependency, it provides a framework called Logback.
	   Logback is kind of a successor to the log4j framework 
	 
	 > We are calling the logging API using slf4j but then slf4j is delegating the logging work itself to 
	    Logback which happens there to be in our class path because we used spring-starter-web dependency
	  
	*/
	Logger logger = LoggerFactory.getLogger(LogController.class);
	
	/*
	 > Configuration of log levels
	 	Default log level of a Spring boot application is info. trace is not up to the level of info. If it is required to log by default
	 	then log level has to be info or above
	*/
	@RequestMapping("/")
	public String home() {
		/*
		 In order to make this trace show, we have to define a property in property file
		 (Check application.properties file)
		 
		 If there is a lot of configurations in the application.property file and it is messing up the property keys,
		 we can have XML configuration instead for defining log levels.
		 
		 We can do this by specifying a certain standard file_name.xml in the class path.
		 Spring boot looks for certain XML files in the class path
		 
		 Names : logback-spring.xml
		 		 logback.xml
		 
		 We do not have to specify huge XML configuration, we can actually do inheriting (inheritable XML)
		 
		 Ex : logback-spring.xml
		 <?xml version="1.0" encoding="UTF-8"?>
		 <configuration>
		 	<include resource="org/springframework/boot/logging/logback/base.xml"/>
		 	<logger name="org.springframework.web" level="DEBUG"/>
		 </configuration>
		 
		*/
		
		logger.trace("home() method logger.trace was accessed");
		
		//Displaying a simple log message
		logger.error("Displaying an error log message");
		return "home rest API called";
	}
	
	/*
	 Logging Summary
	 	- Logging is built in with many Spring starter dependencies
	 	- LoggerFactory.getLogger() for Logger instance
	 	- Uses Logback by default (can be changed)
	 	- Configuration using : 
	 		1. Application property values
	 		2. XML file with naming convention
	*/	
}
