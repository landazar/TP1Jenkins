package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.inti.model.Voyageur;
import com.inti.repository.IVoyageurRepository;
import com.inti.repository.IVoyageurRepository;

@Controller
public class VoyageurController {
	
	@Autowired
	IVoyageurRepository ivr;
	
	@GetMapping("creerVoyageur")
	public String creerVoyageur()
	{
		return "creerVoyageur";
	}
	
	@PostMapping("creerVoyageur")
	public String creerVoyageur(@ModelAttribute("voyageur") Voyageur v)
	{		
		ivr.save(v);
		
		return "redirect:/listeVoyageur";
	}
	
	@GetMapping("listeVoyageur")
	public String listeVoyageur(Model m)
	{
		m.addAttribute("listeV", ivr.findAll());
		
		return "listeVoyageur";
	}
	
	@GetMapping("modifierVoyageur/{id}")
	public String modifierVoyageur(@PathVariable("id") long id, Model m)
	{
		m.addAttribute("voyageur", ivr.getReferenceById(id));
		return "modifierVoyageur";
	}
	
	@PostMapping("modifierVoyageur/{id}")
	public String modifierVoyageur(@ModelAttribute("voyageur") Voyageur v, @PathVariable("id") long id)
	{
		ivr.save(v);
		return "redirect:/listeVoyageur";
	}
	
	@GetMapping("deleteVoyageur/{id}")
	public String deleteVoyageur(@PathVariable("id") long id)
	{
		ivr.delete(ivr.getReferenceById(id));
		return "redirect:/listeVoyageur";
	}

}
