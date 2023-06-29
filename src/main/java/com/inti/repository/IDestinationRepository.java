package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Destination;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Long> {

}
