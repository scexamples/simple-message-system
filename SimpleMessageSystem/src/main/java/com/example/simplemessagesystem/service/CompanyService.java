package com.example.simplemessagesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplemessagesystem.model.Company;
import com.example.simplemessagesystem.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public  Optional<Company> findByName(String name){
		
		return companyRepository.findByName(name);
		
	}

	public List<Company> findAllCompanies() {
		
		return companyRepository.findAll();
		
	}
	
	public  Optional<Company> findById(int id){
		
		return companyRepository.findById(id);
		
	}
}

