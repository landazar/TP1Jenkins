package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Reservation;
import com.inti.repository.IReservationRepository;

@WebMvcTest(controllers = ReservationController.class)
public class ReservationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IReservationRepository irr;
	
	@Test
	public void saveReservation() throws Exception
	{
		mockMvc.perform(get("/creerReservation"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeReservation() throws Exception
	{
		mockMvc.perform(get("/listeReservation"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteReservation() throws Exception
	{
		mockMvc.perform(get("/deleteReservation/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeReservation"))
		.andDo(print());
	}
	
	@Test
	public void saveReservationPost() throws Exception
	{
		mockMvc.perform(post("/creerReservation").sessionAttr("Reservation", new Reservation(LocalDate.parse("2022-06-03"), 15)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeReservation"))
		.andDo(print());
	}
	
	@Test
	public void modifierReservationPost() throws Exception
	{
		mockMvc.perform(post("/modifierReservation/1").sessionAttr("Reservation", new Reservation(LocalDate.parse("2022-06-03"), 15)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeReservation"))
		.andDo(print());
	}

}
