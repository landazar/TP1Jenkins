package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Destination;



@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) >>>>>>>> si on utilise la bdd de mySQL pour test sinon H2
public class DestinationRepositoryTest {
	
	@Autowired
	IDestinationRepository idr;
	
	//--------------------------------Save--------------------------------------
	
		@Test
		public void saveDestinationTest()
		{
			//given
			Destination d1 = new Destination(150, 3);
			
			//when
			Destination destinationSaved = idr.save(d1);
			
			//then
			assertThat(destinationSaved).isNotNull();
			assertThat(destinationSaved.getId()).isGreaterThan(0);
		}
		
		
		//-------------------------------------------Supprimer----------------------------------
		
		@Test
		public void deleteDestinationTest()
		{
			//given
			Destination d1 = new Destination(150, 3);
			Destination destinationSaved = idr.save(d1);
			//when
			
			idr.delete(destinationSaved);
			
			//then
			Assertions.assertThrows(Exception.class, () -> idr.getReferenceById(d1.getId()));
		}
		
		@Test
		public void deleteDestinationNotFoundTest()
		{
			//given
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> idr.delete(null));
		}
		
		//--------------------------------------------Update--------------------------------------------
		
		@Test
		public void updateDestinationTest()
		{
			//given
			Destination d1 = new Destination(150, 3);
			Destination destinationSaved = idr.save(d1);
			//when
			
			destinationSaved.setLatitude(1500);
			Destination destinationUpdated = idr.save(destinationSaved);
			
			//then
			assertThat(destinationUpdated).isNotNull();
			assertThat(destinationSaved.getLatitude()).isEqualTo(1500);
		}
		
		@Test
		public void updateDestinationNullTest()
		{
			//given
			Destination d1 = null;
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> d1.setLatitude(1500));
		}
		
		//---------------------------------------------select------------------------------------------------
		
		@Test
		public void getDestinationTest()
		{
			//given
			Destination d1 = new Destination(150, 3);
			Destination destinationSaved = idr.save(d1);
			//when
			Destination destination = idr.getReferenceById(destinationSaved.getId());
			
			
			
			//then
			assertThat(destination).isNotNull();
			assertThat(destination.getLatitude()).isEqualTo(3);
			assertThat(destination).isEqualTo(destinationSaved);
		}
		
		@Test
		public void getAllDestinationTest()
		{
			//given
			Destination d1 = new Destination(150, 3);
			Destination d2 = new Destination(1500, 3);
			Destination destinationSaved1 = idr.save(d1);
			Destination destinationSaved2 = idr.save(d2);
			
			//when
			List<Destination> listeD = idr.findAll();
			
			//then
			assertThat(listeD).isNotEmpty();
			assertThat(listeD).hasSize(2);
			assertThat(listeD.get(0).getClass()).hasSameClassAs(Destination.class);
		}
		
		@Test
		public void getDestinationListeVideTest()
		{
			//given
			
			
			//when
			List<Destination> listeD = idr.findAll();
			
			//then
			assertThat(listeD).isEmpty();
			assertThat(listeD).hasSize(0);
		}

}
