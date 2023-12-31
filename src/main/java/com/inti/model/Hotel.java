package com.inti.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	@NonNull
	private String nom;
	private int nbEtoile;
	
	public Hotel(@NonNull String nom, int nbEtoile) {
		super();
		this.nom = nom;
		this.nbEtoile = nbEtoile;
	}

	public Hotel(int nbEtoile) {
		super();
		this.nbEtoile = nbEtoile;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_destination")
	@Exclude
	private Destination destination;
	
	@OneToMany(mappedBy = "hotel")
	@Exclude
	private List<Reservation> listeR;

}
