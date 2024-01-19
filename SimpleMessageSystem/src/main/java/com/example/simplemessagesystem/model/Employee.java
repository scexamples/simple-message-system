package com.example.simplemessagesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	 	private String firstName;
	    private String lastName;
	    private String email;
	    
	    @ManyToOne
	    @JoinColumn(name = "company_id")
	    private Company company;

		public Employee() {
			
		}
		
		public Employee(String firstName, String lastName, String email, Company company) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.company = company;
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

}

