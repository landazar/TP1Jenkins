package com.inti.model;

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
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	@Column(nullable = false)
	private long longitude;
	@NonNull
	@Column(nullable = false)
	private long latitude;
	
	@OneToMany(mappedBy = "destination")
	@Exclude
	private List<Hotel> listeH;

}
