package com.example.simplemessagesystem.controller;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.simplemessagesystem.model.Company;
import com.example.simplemessagesystem.model.Message;
import com.example.simplemessagesystem.service.CompanyService;
import com.example.simplemessagesystem.service.MessageService;

@Controller
public class MessageController {
	
	private MessageService messageService;
	
	private CompanyService companyService;
	
	public MessageController(MessageService messageService, CompanyService companyService) {
        this.messageService = messageService;
        this.companyService = companyService;
    }
	
	@ResponseBody
	@PostMapping(value = "/message/{companyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendMessageToCompany(@RequestBody Message message, @PathVariable int companyId) {		
		
		messageService.sendMessageToCompany(message, companyId);		
			
		return ResponseEntity.ok("Success");
		
	}
	
	@ResponseBody
	@PostMapping(value = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendMessageToAllCompanies(@RequestBody Message message) {
				
		messageService.sendMessageToAllCompanies(message);
					
		return ResponseEntity.ok("Success");
		
	}
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Company> companies = companyService.findAllCompanies();
		model.addAttribute("companies", companies);
		
		return "simpleMessage";
		
	}
	
	@ResponseBody
	@PostMapping(value = "/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody MultiValueMap<String, String> formData) {
		
		String subject = formData.getFirst("subject");
		String text = formData.getFirst("text");
		int companyId = Integer.parseInt(formData.getFirst("companyId"));
		String allCompanies = formData.getFirst("allCompanies");
		
		if(companyId != 0 && allCompanies != null) {
			throw new IllegalArgumentException("Please select either one company or all companies");
		}
		
		if(allCompanies != null) {
			messageService.sendMessageToAllCompanies(new Message(subject, text));
		}else {
			messageService.sendMessageToCompany(new Message(subject, text), companyId);
		}
		
		return ResponseEntity.ok("Success");
		
		
    }
	
}

