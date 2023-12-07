package com.example.studentservice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootStudentServiceApplication {

    Logger log = LoggerFactory.getLogger(SpringBootStudentServiceApplication.class);
    @Value("${deployed.from}")
    private String deployedFrom;


	@RequestMapping(value = "/echoStudentName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name) {
	    log.info("Logging name "+name+" from -->"+deployedFrom);
		return "Hello " + name + " .Welcome to Azure spring apps. Current time is :: " + new Date()+" and deployed from ::"+deployedFrom;
	}

	@RequestMapping(value = "/getStudentDetails/{name}")
	public Student getStudentDetails(@PathVariable(name = "name") String name) {
        log.info("Logging name "+name+" from -->"+deployedFrom);
	    return new Student(name, "Pune", "MCA");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudentServiceApplication.class, args);
	}
}

class Student {
	String name;
	String address;
	String cls;

	public Student(String name, String address, String cls) {
		super();
		this.name = name;
		this.address = address;
		this.cls = cls;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCls() {
		return cls;
	}

}
