package net.anastasia.simien.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity                                                                                          
@Table(name = "requestPayment")                                                                             
public class RequestPayment {
	@Id                                                                                          
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                          
	private int id;   
	
	@Column(name = "inquiryId", nullable = false)                                                 
	private int inquiryId;  
	
	@Column(name = "deposit", nullable = false)                                                 
	private double deposit; 
	
	@Column(name = "requestDate", nullable = false)                                                 
	private Date requestDate; 
	
	@Column(name = "clientMail", nullable = false)                                                 
	private String clientMail;
	

	@Column(name = "mailText", nullable = false)                                                 
	private String mailText;
	
	
	public RequestPayment(){
		
	}
	
	public RequestPayment(int inquiryId, double deposit, Date requestDate, String mailText) {
		super();
		this.inquiryId = inquiryId;
		this.deposit = deposit;
		this.requestDate = requestDate;
		this.mailText = mailText;
	}

	public int getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getClientMail() {
		return clientMail;
	}

	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	
	public String getMailText() {
		return mailText;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	} 
	
	
	
	

}
