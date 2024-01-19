package com.example.simplemessagesystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.simplemessagesystem.exception.DataNotFoundException;
import com.example.simplemessagesystem.exception.InvalidMethodArgumentException;
import com.example.simplemessagesystem.model.Company;
import com.example.simplemessagesystem.model.Message;

@Service
public class MessageService {
	
	private JavaMailSender javaMailSender;
	
	private CompanyService companyService;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	public MessageService(CompanyService companyService, JavaMailSender javaMailSender) {
		this.companyService = companyService;
		this.javaMailSender = javaMailSender;
	}
		
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		javaMailSender.send(simpleMailMessage);
	}
	

	public void sendMessageToCompany(Message message, int companyId) {

		Optional<Company> optCompany = companyService.findById(companyId);				
		optCompany.orElseThrow(() -> new InvalidMethodArgumentException());
		
        Company company = optCompany.get();
        
        company.getEmployees()
					.stream()
					.forEach(employee->sendEmail(employee.getEmail(), message.getSubject(), message.getText()));
		
	}

	public void sendMessageToAllCompanies(Message message) {

		List<Company> companies = companyService.findAllCompanies();
		
		if(companies.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			companies.stream()
					.forEach(company->company.getEmployees()
												.stream()
												.forEach(employee->sendEmail(employee.getEmail(), message.getSubject(), message.getText())));
		}	
		
	}

}

