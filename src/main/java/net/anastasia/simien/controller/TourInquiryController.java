package net.anastasia.simien.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.anastasia.simien.exception.ResourceNotFoundException;
import net.anastasia.simien.controller.GuideController;
import net.anastasia.simien.model.Guide;
import net.anastasia.simien.model.GuideRepository;
import net.anastasia.simien.model.TourInquiry;
import net.anastasia.simien.model.TourInquiryRepository;
import net.anastasia.simien.utilities.Mailing;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/v1")
public class TourInquiryController {
	 org.slf4j.Logger logger = LoggerFactory.getLogger(GuideController.class);
	 
		@Autowired
		private TourInquiryRepository tourInquiryRepository;
		@Autowired
		private GuideRepository guideRepository;
		
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		@GetMapping("/tourInquiries")
		public List<TourInquiry> getAllTourInquiries() {
			return tourInquiryRepository.findAll();
		}
		
		@CrossOrigin(origins = "*")
		@GetMapping("/tourInquiries/{tourInquiryId}")
		public ResponseEntity<TourInquiry> getTourInquiryById(@PathVariable(value = "tourInquiryId") Integer tourInquiryId)
				throws ResourceNotFoundException {
			
			
			TourInquiry tourInquiry = tourInquiryRepository.findById(tourInquiryId)
					.orElseThrow(() -> new ResourceNotFoundException("Tour inquiry not found for this id : " + tourInquiryId));
			
			logger.debug(tourInquiry.toString());
			return ResponseEntity.ok().body(tourInquiry);
		}

		@CrossOrigin(origins = "*", allowedHeaders = "*")
		@PostMapping("/tourInquiries")
		//public TourInquiry createTourInquiry(@Valid @RequestBody TourInquiry tourInquiry) {
		public TourInquiry createTourInquiry(@Valid @RequestBody TourInquiry tourInquiry) {
			String textForTheMail="";
			TourInquiry ti =  tourInquiryRepository.save(tourInquiry);
			Mailing mailing = new Mailing("smtp.zoho.eu", 587, "info@simienpark.org", "nastiapasta");
			//String tourDate = ti.getDate().getDate()+" "+ti.getDate().getMonth()+" "+ti.getDate().getYear(); 
			SimpleDateFormat formatter = new SimpleDateFormat("EEEEE dd MMMMM yyyy", new Locale("en")); 
			//sending mails about new tour inquiry to all the guides
			

			
			textForTheMail = "Tour inquiry number: "+ti.getId()+"ETH"+" \n"+"Tourist: "+ti.getName()+". \n"+ " For how many days: "+ ti.getDays()+". \n"+" Tour starts: "+formatter.format(ti.getDate())+". \n"+" How many persons: "
			   +ti.getPersons()+". \n"+" Message from the tourist: "+ti.getMessage();
			
			for(Guide aGuide : guideRepository.findAll())
			{
				//System.out.println(aGuide.getEmail());
				mailing.sendMail(textForTheMail, aGuide.getEmail(), "URGENT REPLY NEEDED-new tour request");
			}


			return ti;
			
		}
		
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		@PutMapping("/tourInquiries/{tourInquiryId}")
		public ResponseEntity<TourInquiry> updateTourInquiry(@PathVariable(value = "tourInquiryId") Integer tourInquiryId,
				@Valid @RequestBody TourInquiry tourInquiryDetails) throws ResourceNotFoundException {
			TourInquiry tourInquiry = tourInquiryRepository.findById(tourInquiryId)
					.orElseThrow(() -> new ResourceNotFoundException("Tour inquiry not found for this id :: " + tourInquiryId));

			tourInquiry.setName(tourInquiryDetails.getName());
			final TourInquiry updatedTourInquiry = tourInquiryRepository.save(tourInquiry);
			return ResponseEntity.ok(updatedTourInquiry);
		}
		
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		@DeleteMapping("/tourInquiries/{tourInquiryId}")
		public Map<String, Boolean> deleteTourInquiry(@PathVariable(value = "tourInquiryId") Integer tourInquiryId)
				throws ResourceNotFoundException {
			TourInquiry tourInquiry = tourInquiryRepository.findById(tourInquiryId)
					.orElseThrow(() -> new ResourceNotFoundException("Tour inquiry not found for this id :: " + tourInquiryId));

			tourInquiryRepository.delete(tourInquiry);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}


}
