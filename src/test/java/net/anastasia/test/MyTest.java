package net.anastasia.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import net.anastasia.simien.controller.GuideController;
import net.anastasia.simien.model.Guide;
import net.anastasia.simien.model.GuideRepository;
import net.anastasia.simien.utilities.Mailing;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GuideRepository guideRepository;

	@Test
	public void testGuides() {
		System.out.println("testing mail");
		Guide alex = new Guide("Alex", "Alex123","333", "anastasiagrbz@gmail.com", 67676763, true, false);
		Guide bob = new Guide("Bob", "Bob3","444","anastasiagrbz@gmail.com", 676999763, true, true);
	    entityManager.persist(alex);
	    entityManager.persist(bob);
	    entityManager.flush();

		// Mailing mailing = new Mailing("smtp.zoho.eu", 587, "info@simienpark.org",
		// "nastiapasta");
		// mailing.sendMail("&&&");
		// Mailing mailing = new Mailing("smtp.zoho.eu", 587, "info@simienpark.org",
		// "nastiapasta");
		// sending mails about new tour inquiry to all the guides

		//List<Guide> listGuides = guideRepository.findAll();
		for(Guide aGuide : guideRepository.findAll())
		{
			System.out.println(aGuide.getEmail());
		}

	}

//	 @Test
//	 public void testMail()
//	 {assertEquals(true, false);}
}
