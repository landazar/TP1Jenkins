package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Reservation;



@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) >>>>>>>> si on utilise la bdd de mySQL pour test sinon H2
public class ReservationRepositoryTest {
	
	@Autowired
	IReservationRepository irr;
	
	//--------------------------------Save--------------------------------------
	
		@Test
		public void saveReservationTest()
		{
			//given
			Reservation r1 = new Reservation(LocalDate.parse("2022-06-03"), 15);
			
			//when
			Reservation reservationSaved = irr.save(r1);
			
			//then
			assertThat(reservationSaved).isNotNull();
			assertThat(reservationSaved.getId()).isGreaterThan(0);
		}
		
		
		//-------------------------------------------Supprimer----------------------------------
		
		@Test
		public void deleteReservationTest()
		{
			//given
			Reservation r1 = new Reservation(LocalDate.parse("2022-06-03"), 15);
			Reservation reservationSaved = irr.save(r1);
			//when
			
			irr.delete(reservationSaved);
			
			//then
			Assertions.assertThrows(Exception.class, () -> irr.getReferenceById(r1.getId()));
		}
		
		@Test
		public void deleteReservationNotFoundTest()
		{
			//given
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> irr.delete(null));
		}
		
		//--------------------------------------------Update--------------------------------------------
		
		@Test
		public void updateReservationTest()
		{
			//given
			Reservation r1 = new Reservation(LocalDate.parse("2022-06-03"), 15);
			Reservation reservationSaved = irr.save(r1);
			//when
			
			reservationSaved.setNbJours(13);
			Reservation reservationUpdated = irr.save(reservationSaved);
			
			//then
			assertThat(reservationUpdated).isNotNull();
			assertThat(reservationSaved.getNbJours()).isEqualTo(13);
		}
		
		@Test
		public void updateReservationNullTest()
		{
			//given
			Reservation r1 = null;
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> r1.setNbJours(13));
		}
		
		//---------------------------------------------select------------------------------------------------
		
		@Test
		public void getReservationTest()
		{
			//given
			Reservation r1 = new Reservation(LocalDate.parse("2022-06-03"), 15);
			Reservation reservationSaved = irr.save(r1);
			//when
			Reservation reservation = irr.getReferenceById(reservationSaved.getId());
			
			
			
			//then
			assertThat(reservation).isNotNull();
			assertThat(reservation.getNbJours()).isEqualTo(15);
			assertThat(reservation).isEqualTo(reservationSaved);
		}
		
		@Test
		public void getAllReservationTest()
		{
			//given
			Reservation r1 = new Reservation(LocalDate.parse("2022-06-03"), 15);
			Reservation r2 = new Reservation(LocalDate.parse("2022-06-03"), 10);
			Reservation reservationSaver1 = irr.save(r1);
			Reservation reservationSaver2 = irr.save(r2);
			
			//when
			List<Reservation> listeR = irr.findAll();
			
			//then
			assertThat(listeR).isNotEmpty();
			assertThat(listeR).hasSize(2);
			assertThat(listeR.get(0).getClass()).hasSameClassAs(Reservation.class);
		}
		
		@Test
		public void getReservationListeVideTest()
		{
			//given
			
			
			//when
			List<Reservation> listeR = irr.findAll();
			
			//then
			assertThat(listeR).isEmpty();
			assertThat(listeR).hasSize(0);
		}

}
