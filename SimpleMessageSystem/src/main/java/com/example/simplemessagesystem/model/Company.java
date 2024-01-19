package com.example.simplemessagesystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Company {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
   

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
    
 
    public Company() {
		
	}
    
    public Company(String name) {
		this.name = name;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	//sync bidirectional association
	public void addEmployee(Employee employee) {
		employees.add(employee);
		if(employee.getCompany() == null) {
			employee.setCompany(this);
		}		
	}
	
	//sync bidirectional association
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
		employee.setCompany(null);
	}


}
