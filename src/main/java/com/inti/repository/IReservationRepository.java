package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Reservation;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long>{

}
