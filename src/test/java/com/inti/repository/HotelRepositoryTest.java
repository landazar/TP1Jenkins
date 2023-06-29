package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Hotel;
import com.inti.model.Hotel;
import com.inti.model.Hotel;
import com.inti.model.Hotel;


@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) >>>>>>>> si on utilise la bdd de mySQL pour test sinon H2
public class HotelRepositoryTest {
	
	@Autowired
	IHotelRepository ihr;
	
	//--------------------------------Save--------------------------------------
	
	@Test
	public void saveHotelTest()
	{
		//given
		Hotel h1 = new Hotel("Papilon lagoon", 3);
		
		//when
		Hotel hotelSaved = ihr.save(h1);
		
		//then
		assertThat(hotelSaved).isNotNull();
		assertThat(hotelSaved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void saveHotelSansRequiredTest()
	{
		//given
		Hotel h1 = new Hotel(3);
		
		//when
		//then
		Assertions.assertThrows(Exception.class, () -> ihr.save(h1));
	}
	
	@Test
	public void saveHotelOnlyRequiredTest()
	{
		//given
		Hotel h1 = new Hotel("Papillon lagoon");
		
		//when
		Hotel hotelSaved = ihr.save(h1);
		
		//then
		assertThat(hotelSaved).isNotNull();
		assertThat(hotelSaved.getId()).isGreaterThan(0);
	}
	
	//-------------------------------------------Supprimer----------------------------------
	
	@Test
	public void deleteHotelTest()
	{
		//given
		Hotel h1 = new Hotel("Papilon lagoon", 3);
		Hotel hotelSaved = ihr.save(h1);
		//when
		
		ihr.delete(hotelSaved);
		
		//then
		Assertions.assertThrows(Exception.class, () -> ihr.getReferenceById(h1.getId()));
	}
	
	@Test
	public void deleteHotelNotFoundTest()
	{
		//given
		
		//when
		
		//then
		Assertions.assertThrows(Exception.class, () -> ihr.delete(null));
	}
	
	//--------------------------------------------Update--------------------------------------------
	
	@Test
	public void updateHotelTest()
	{
		//given
		Hotel h1 = new Hotel("Papilon lagoon", 3);
		Hotel hotelSaved = ihr.save(h1);
		//when
		
		hotelSaved.setNom("Westminster");
		Hotel hotelUpdated = ihr.save(hotelSaved);
		
		//then
		assertThat(hotelUpdated).isNotNull();
		assertThat(hotelSaved.getNom()).isEqualTo("Westminster");
	}
	
	@Test
	public void updateHotelNullTest()
	{
		//given
		Hotel h1 = null;
		
		//when
		
		//then
		Assertions.assertThrows(Exception.class, () -> h1.setNom("Westminster"));
	}
	
	//---------------------------------------------select------------------------------------------------
	
	@Test
	public void getHotelTest()
	{
		//given
		Hotel c1 = new Hotel("Papilon lagoon", 3);
		Hotel hotelSaved = ihr.save(c1);
		//when
		Hotel hotel = ihr.getReferenceById(hotelSaved.getId());
		
		
		
		//then
		assertThat(hotel).isNotNull();
		assertThat(hotel.getNom()).isEqualTo("Papilon lagoon");
		assertThat(hotel).isEqualTo(hotelSaved);
	}
	
	@Test
	public void getAllHotelTest()
	{
		//given
		Hotel h1 = new Hotel("Papilon lagoon", 3);
		Hotel h2 = new Hotel("Westminster", 3);
		Hotel hotelSaved1 = ihr.save(h1);
		Hotel hotelSaved2 = ihr.save(h2);
		
		//when
		List<Hotel> listeH = ihr.findAll();
		
		//then
		assertThat(listeH).isNotEmpty();
		assertThat(listeH).hasSize(2);
		assertThat(listeH.get(0).getClass()).hasSameClassAs(Hotel.class);
	}
	
	@Test
	public void getHotelListeVideTest()
	{
		//given
		
		
		//when
		List<Hotel> listeH = ihr.findAll();
		
		//then
		assertThat(listeH).isEmpty();
		assertThat(listeH).hasSize(0);
	}

}
