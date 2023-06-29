package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Destination;
import com.inti.repository.IDestinationRepository;

@WebMvcTest(controllers = DestinationController.class)
public class DestinationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IDestinationRepository idr;
	
	@Test
	public void saveDestination() throws Exception
	{
		mockMvc.perform(get("/creerDestination"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeDestination() throws Exception
	{
		mockMvc.perform(get("/listeDestination"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteDestination() throws Exception
	{
		mockMvc.perform(get("/deleteDestination/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeDestination"))
		.andDo(print());
	}
	
	@Test
	public void saveDestinationPost() throws Exception
	{
		mockMvc.perform(post("/creerDestination").sessionAttr("destination", new Destination(154, 3)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeDestination"))
		.andDo(print());
	}
	
	@Test
	public void modifierDestinationPost() throws Exception
	{
		mockMvc.perform(post("/modifierDestination/1").sessionAttr("destination", new Destination(154, 3)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeDestination"))
		.andDo(print());
	}

}
