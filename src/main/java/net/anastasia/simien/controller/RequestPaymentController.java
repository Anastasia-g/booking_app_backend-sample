package net.anastasia.simien.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.anastasia.simien.model.RequestPayment;
import net.anastasia.simien.model.RequestPaymentRepository;
import net.anastasia.simien.utilities.Mailing;

@RestController
@RequestMapping("/api/v1")
public class RequestPaymentController {
	@Autowired
	private RequestPaymentRepository requestPaymentRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/paymentRequests")
	public RequestPayment createRequestPayment(@Valid @RequestBody RequestPayment requestPayment) {
		
		RequestPayment rp =  requestPaymentRepository.save(requestPayment);
		Mailing mailing = new Mailing("smtp.zoho.eu", 587, "info@simienpark.org", "nastiapasta");
		mailing.sendMail(requestPayment.getMailText(), requestPayment.getClientMail(), "Payment request for your tour to Ethopia");
		return rp;
		
	}

}
