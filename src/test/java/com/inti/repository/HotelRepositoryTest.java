package com.inti.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) >>>>>>>> si on utilise la bdd de mySQL pour test sinon H2
public class HotelRepositoryTest {
	
	@Autowired
	IHotelRepository ihr;

}
