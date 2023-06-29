package com.inti.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Table
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	@NonNull
	private LocalDate date;
	@Column(nullable = false)
	@NonNull
	private int nbJours;
	
	@ManyToOne
	@JoinColumn(name = "id_hotel")
	@Exclude
	private Hotel hotel;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "id_reservation")
	private Voyageur voyageur;

}
