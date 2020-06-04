package net.anastasia.simien.controller;


import java.util.HashMap;
import java.util.List;
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
import net.anastasia.simien.model.Guide;
import net.anastasia.simien.model.GuideRepository;

@RestController
@RequestMapping("/api/v1")
public class GuideController {
	
	 org.slf4j.Logger logger = LoggerFactory.getLogger(GuideController.class);
	 
	@Autowired
	private GuideRepository guideRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/guides")
	public List<Guide> getAllGuides() {
		return guideRepository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/guides/{guideId}")
	public ResponseEntity<Guide> getGuideById(@PathVariable(value = "guideId") Integer guideId)
			throws ResourceNotFoundException {
		
		
		Guide guide = guideRepository.findById(guideId)
				.orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + guideId));
		
		logger.debug(guide.toString());
		return ResponseEntity.ok().body(guide);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/guides")
	public Guide createGuide(@Valid @RequestBody Guide guide) {
		return guideRepository.save(guide);
	}
	
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/guides/{guideId}")
	public ResponseEntity<Guide> updateGuide(@PathVariable(value = "guideId") Integer guideId,
			@Valid @RequestBody Guide guideDetails) throws ResourceNotFoundException {
		Guide guide = guideRepository.findById(guideId)
				.orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + guideId));

//		guide.setName(guideDetails.getName());
//		guide.setNickname(guideDetails.getNickname());
//		//guide.setPassword(guideDetails.getPassword());
//		guide.setEmail(guideDetails.getEmail());
//		guide.setPhone(guideDetails.getPhone());
//		guide.setEnglish(guideDetails.isEnglish());
//		guide.setFrench(guideDetails.isFrench());
		guideDetails.setId(guideId);
		
		final Guide updatedGuide = guideRepository.save(guideDetails);
		return ResponseEntity.ok(updatedGuide);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/guides/{guideId}")
	public Map<String, Boolean> deleteGuide(@PathVariable(value = "guideId") Integer guideId)
			throws ResourceNotFoundException {
		Guide guide = guideRepository.findById(guideId)
				.orElseThrow(() -> new ResourceNotFoundException("Guide not found for this id :: " + guideId));

		guideRepository.delete(guide);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}

