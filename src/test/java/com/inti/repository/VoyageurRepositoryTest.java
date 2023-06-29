package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Voyageur;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) >>>>>>>> si on utilise la bdd de mySQL pour test sinon v2
public class VoyageurRepositoryTest {
	
	@Autowired
	IVoyageurRepository ivr;
	
	//--------------------------------Save--------------------------------------
	
		@Test
		public void saveVoyageurTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas", 27);
			
			//when
			Voyageur voyageurSaved = ivr.save(v1);
			
			//then
			assertThat(voyageurSaved).isNotNull();
			assertThat(voyageurSaved.getId()).isGreaterThan(0);
		}
		
		@Test
		public void saveVoyageurSansRequiredTest()
		{
			//given
			Voyageur v1 = new Voyageur(3);
			
			//when
			//then
			Assertions.assertThrows(Exception.class, () -> ivr.save(v1));
		}
		
		@Test
		public void saveVoyageurOnlyRequiredTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas");
			
			//when
			Voyageur voyageurSaved = ivr.save(v1);
			
			//then
			assertThat(voyageurSaved).isNotNull();
			assertThat(voyageurSaved.getId()).isGreaterThan(0);
		}
		
		//-------------------------------------------Supprimer----------------------------------
		
		@Test
		public void deleteVoyageurTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas", 27);
			Voyageur voyageurSaved = ivr.save(v1);
			//when
			
			ivr.delete(voyageurSaved);
			
			//then
			Assertions.assertThrows(Exception.class, () -> ivr.getReferenceById(v1.getId()));
		}
		
		@Test
		public void deleteVoyageurNotFoundTest()
		{
			//given
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> ivr.delete(null));
		}
		
		//--------------------------------------------Update--------------------------------------------
		
		@Test
		public void updateVoyageurTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas", 27);
			Voyageur voyageurSaved = ivr.save(v1);
			//when
			
			voyageurSaved.setNom("Dubois");
			Voyageur voyageurUpdated = ivr.save(voyageurSaved);
			
			//then
			assertThat(voyageurUpdated).isNotNull();
			assertThat(voyageurSaved.getNom()).isEqualTo("Dubois");
		}
		
		@Test
		public void updateVoyageurNullTest()
		{
			//given
			Voyageur v1 = null;
			
			//when
			
			//then
			Assertions.assertThrows(Exception.class, () -> v1.setNom("Dubois"));
		}
		
		//---------------------------------------------select------------------------------------------------
		
		@Test
		public void getVoyageurTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas", 27);
			Voyageur voyageurSaved = ivr.save(v1);
			//when
			Voyageur voyageur = ivr.getReferenceById(voyageurSaved.getId());
			
			
			
			//then
			assertThat(voyageur).isNotNull();
			assertThat(voyageur.getNom()).isEqualTo("Dupont");
			assertThat(voyageur).isEqualTo(voyageurSaved);
		}
		
		@Test
		public void getAllVoyageurTest()
		{
			//given
			Voyageur v1 = new Voyageur("Dupont", "Nicolas", 27);
			Voyageur v2 = new Voyageur("Dubois", "Paul", 27);
			Voyageur voyageurSaved1 = ivr.save(v1);
			Voyageur voyageurSaved2 = ivr.save(v2);
			
			//when
			List<Voyageur> listeV = ivr.findAll();
			
			//then
			assertThat(listeV).isNotEmpty();
			assertThat(listeV).hasSize(2);
			assertThat(listeV.get(0).getClass()).hasSameClassAs(Voyageur.class);
		}
		
		@Test
		public void getVoyageurListeVideTest()
		{
			//given
			
			
			//when
			List<Voyageur> listeV = ivr.findAll();
			
			//then
			assertThat(listeV).isEmpty();
			assertThat(listeV).hasSize(0);
		}

}
