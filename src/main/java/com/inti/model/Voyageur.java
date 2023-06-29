package com.inti.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Voyageur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)@NonNull
	private String nom;
	@Column(nullable = false) @NonNull
	private String prenom;
	private int age;
	
	@Exclude
	@OneToMany(mappedBy = "voyageur")
	private List<Reservation> listeR;
	
	public Voyageur(@NonNull String nom, @NonNull String prenom, int age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	public Voyageur(int age) {
		super();
		this.age = age;
	}
	
	

}
