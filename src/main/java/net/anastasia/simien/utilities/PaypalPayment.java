package net.anastasia.simien.utilities;

import com.paypal.base.rest.APIContext;

import com.paypal.base.rest.PayPalRESTException;

import net.anastasia.simien.controller.PaymentController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;

public class PaypalPayment {
	static String clientId = "AQWiW7-xc64jYmFIBngoWVHBNnAQ91z677GLOeKfIhGbaPN-Bx4ZlNi7vTGmyuNvvtQm4JJ3LuI3mJ3o";
	static String clientSecret = "EIRm1-yHRXeALntCrMgEzvptdEJKrqe4AMRzgOhq65JMswvk9-yS6DN-baGlgAUWTWNksvLY_IC0MiiA";
    static  Logger logger = LoggerFactory.getLogger(PaypalPayment.class);   
	static APIContext context = new APIContext(clientId, clientSecret, "sandbox");

	public static String pay(double i, String paymentDescr) {
		// TODO Auto-generated method stub
		
		
		///DEFINE PAYMENT


		// Set payer details
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:3000/cancel");
		redirectUrls.setReturnUrl("http://localhost:3000/process");

		// Set payment details
		//Details details = new Details();
//		details.setShipping("1");
//		details.setSubtotal("5");
//		details.setTax("1");

		// Payment amount
		Amount amount = new Amount();
		amount.setCurrency("EUR");
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(""+i);
		//amount.setDetails(details);

		// Transaction information
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		  .setDescription(paymentDescr);

		// Add transaction to a list
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		// Add payment details
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);
		
		
		///////////////////CREATE
		
		// Create payment
		String s = null;
		try {
			APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
		  Payment createdPayment = payment.create(apiContext);

		  Iterator<Links> links = createdPayment.getLinks().iterator();
		  while (links.hasNext()) {
		    Links link = links.next();
		    if (link.getRel().equalsIgnoreCase("approval_url")) {
		    	logger.info("THE PAYPAL LINK FOR THE USER IS " + link.getHref());
		      // Redirect the customer to link.getHref()
		    	s=link.getHref();
		    	
		    }
		  }
		} catch (PayPalRESTException e) {
		    System.err.println(e.getDetails());
		    
		}
		return s;
		
		
		
	}

}
