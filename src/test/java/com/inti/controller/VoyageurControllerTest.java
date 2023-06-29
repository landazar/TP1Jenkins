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

import com.inti.model.Voyageur;
import com.inti.repository.IVoyageurRepository;

@WebMvcTest(controllers = VoyageurController.class)
public class VoyageurControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IVoyageurRepository ivr;
	
	@Test
	public void saveVoyageur() throws Exception
	{
		mockMvc.perform(get("/creerVoyageur"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeVoyageur() throws Exception
	{
		mockMvc.perform(get("/listeVoyageur"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteVoyageur() throws Exception
	{
		mockMvc.perform(get("/deleteVoyageur/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeVoyageur"))
		.andDo(print());
	}
	
	
	//------------------------post--------------------------
	
	@Test
	public void saveVoyageurPost() throws Exception
	{
		mockMvc.perform(post("/creerVoyageur").sessionAttr("Voyageur", new Voyageur("Dupont", "Nicolas", 27)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeVoyageur"))
		.andDo(print());
	}
	
	@Test
	public void modifierVoyageurPost() throws Exception
	{
		mockMvc.perform(post("/modifierVoyageur/1").sessionAttr("Voyageur", new Voyageur("Dupont", "Nicolas", 27)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeVoyageur"))
		.andDo(print());
	}

}
