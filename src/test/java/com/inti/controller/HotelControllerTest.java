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

import com.inti.model.Hotel;
import com.inti.repository.IHotelRepository;

@WebMvcTest(controllers = HotelController.class)
public class HotelControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IHotelRepository ihr;
	
	@Test
	public void saveHotel() throws Exception
	{
		mockMvc.perform(get("/creerHotel"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeHotel() throws Exception
	{
		mockMvc.perform(get("/listeHotel"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteHotel() throws Exception
	{
		mockMvc.perform(get("/deleteHotel/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotel"))
		.andDo(print());
	}
	
//	@Test
//	public void modifierHotel() throws Exception
//	{
//		Hotel c1 = new Hotel("Papillon lagoon", 3);
//		
//		Hotel HotelSaved = icr.save(c1);
//		
//		mockMvc.perform(get("/modifierHotel/1").requestAttr("Hotel", HotelSaved))
//		.andExpect(status().isOk())
//		.andDo(print());
//	}
	
	//------------------------post--------------------------
	
	@Test
	public void saveHotelPost() throws Exception
	{
		mockMvc.perform(post("/creerHotel").sessionAttr("hotel", new Hotel("Papillon lagoon", 3)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotel"))
		.andDo(print());
	}
	
	@Test
	public void modifierHotelPost() throws Exception
	{
		mockMvc.perform(post("/modifierHotel/1").sessionAttr("hotel", new Hotel("Papillon lagoon", 3)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotel"))
		.andDo(print());
	}

}


