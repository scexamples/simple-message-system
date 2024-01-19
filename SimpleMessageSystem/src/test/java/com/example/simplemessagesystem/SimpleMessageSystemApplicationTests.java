package com.example.simplemessagesystem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.simplemessagesystem.controller.MessageController;
import com.example.simplemessagesystem.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleMessageSystemApplicationTests {

	private static final int COMPANY_ID = 1;

	private MessageController controller = mock(MessageController.class);
		
	@Autowired
	private MockMvc mockMvc;
	  	 
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void should_SendMessage_When_ValidRequest() throws Exception {
		
		mockMvc.perform(post("/message/"+ COMPANY_ID)
			    .contentType(MediaType.APPLICATION_JSON)
			    .content(new ObjectMapper().writeValueAsString(new Message("subject", "text")))
			    .characterEncoding("utf-8"))
			    .andExpect(status().isOk())
			    .andReturn();
	}
	
	@Test
	public void should_Fail_When_InvalidRequest() throws Exception {
	  		
		mockMvc.perform(post("/message/"+ COMPANY_ID)
			    .contentType(MediaType.APPLICATION_JSON)
			    .characterEncoding("utf-8"))
			    .andExpect(status().isBadRequest())
			    .andReturn();	
		
	}
	

}
