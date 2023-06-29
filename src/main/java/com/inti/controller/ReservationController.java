package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Reservation;
import com.inti.repository.IReservationRepository;

@Controller
public class ReservationController {
	
	@Autowired
	IReservationRepository irr;
	
	@GetMapping("creerReservation")
	public String creerReservation()
	{
		return "creerReservation";
	}
	
	@PostMapping("creerReservation")
	public String creerReservation(@ModelAttribute("reservation") Reservation r)
	{		
		irr.save(r);
		
		return "redirect:/listeReservation";
	}
	
	@GetMapping("listeReservation")
	public String listeReservation(Model m)
	{
		m.addAttribute("listeR", irr.findAll());
		
		return "listeReservation";
	}
	
	@GetMapping("modifierReservation/{id}")
	public String modifierReservation(@PathVariable("id") long id, Model m)
	{
		m.addAttribute("reservation", irr.getReferenceById(id));
		return "modifierReservation";
	}
	
	@PostMapping("modifierReservation/{id}")
	public String modifierReservation(@ModelAttribute("Reservation") Reservation r, @PathVariable("id") long id)
	{
		irr.save(r);
		return "redirect:/listeReservation";
	}
	
	@GetMapping("deleteReservation/{id}")
	public String deleteReservation(@PathVariable("id") long id)
	{
		irr.delete(irr.getReferenceById(id));
		return "redirect:/listeReservation";
	}

}
