package com.example.simplemessagesystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.simplemessagesystem.model.Company;
import com.example.simplemessagesystem.model.Employee;
import com.example.simplemessagesystem.repository.CompanyRepository;

@SpringBootApplication
@EnableJpaRepositories
public class SimpleMessageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMessageSystemApplication.class, args);
		
	}

	@Bean
	  public CommandLineRunner run(CompanyRepository repository) {
	    return (args) -> {
	    	
	    	Company c1 = new Company("Staples");
	    	Company c2 = new Company("OfficeDepot");
	    	
	    	c1.addEmployee(new Employee("My", "Surname", "myEmail@live.com", c1));
	    	c2.addEmployee(new Employee("James", "Bond", "bond@gmail.com", c2));
	    	c2.addEmployee(new Employee("Money", "Penny", "penny@outlook.com", c2));
	    	
	        c1 = repository.save(c1);
	    	c2 = repository.save(c2);
	    	
//	    	System.out.println((repository.findById(c1.getId())).get().getEmployees().size());
//	    	System.out.println((repository.findById(c2.getId())).get().getEmployees().size());
	   
	    };

	}
}
