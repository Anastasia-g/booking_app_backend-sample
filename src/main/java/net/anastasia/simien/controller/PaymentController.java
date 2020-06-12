package net.anastasia.simien.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import net.anastasia.simien.model.Payment;
import net.anastasia.simien.model.PaymentRepository;
import net.anastasia.simien.model.RequestPayment;
import net.anastasia.simien.model.RequestPaymentRepository;
import org.springframework.core.env.Environment;
import net.anastasia.simien.utilities.PaypalPayment;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	

    Logger logger = LoggerFactory.getLogger(PaymentController.class);   
    @Autowired
    private Environment env;

	 
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired 
	private RequestPaymentRepository requestPaymentRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/payments")
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/makepayment/{paymentId}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "paymentId") Integer paymentId)
			throws ResourceNotFoundException {
		
		
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		
		logger.debug(payment.toString());
		return ResponseEntity.ok().body(payment);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getdepositamount/{inquiryId}")
	public double getDepositAmountByInquiryId(@PathVariable(value = "inquiryId") Integer inquiryId)
			throws ResourceNotFoundException {
		
		RequestPayment rp = requestPaymentRepository.findByInquiryId(inquiryId);
		
		//logger.debug(payment.toString());
		return rp.getDeposit();
	}
	

	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/makepayment")
	public String createPayment(@Valid @RequestBody Payment payment) {
		//logger.debug("SAVING PAYMENT");
		Payment pt = paymentRepository.save(payment);
		//return paymentRepository.save(payment);
		logger.info("SAVING PAYMENT" + pt.getPayment());
		
		return PaypalPayment.pay(pt.getPayment(), "Tour deposit", env.getProperty("paypalMethod"), env.getProperty("processURL"), env.getProperty("cancelURL"));
		//return pt;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/payments/{paymentId}")
	public ResponseEntity<Payment> updatePayment(@PathVariable(value = "paymentId") Integer paymentId,
			@Valid @RequestBody Payment paymentDetails) throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

		//payment.setName(paymentDetails.getName());
		final Payment updatedPayment = paymentRepository.save(payment);
		return ResponseEntity.ok(updatedPayment);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/payments/{paymentId}")
	public Map<String, Boolean> deletePayment(@PathVariable(value = "paymentId") Integer paymentId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

		paymentRepository.delete(payment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}

