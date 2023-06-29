package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Voyageur;

@Repository
public interface IVoyageurRepository extends JpaRepository<Voyageur, Long>{

}
