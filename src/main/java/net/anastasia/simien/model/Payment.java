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
@Table(name = "payment")                                                                           
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")   
public class Payment {
	@Id                                                                                          
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                          
	private int id;    
	
	
	@Column(name = "payment", nullable = false)                                                 
	private double payment;  
	
	@Column(name = "paymentDate", nullable = false)                                                 
	private Date paymentDate;
	
	@Column(name = "inquiryId", nullable = false)                                                 
	private int inquiryId; 
	
	public Payment(){
		
	}
	
	

	public Payment(int id, double payment, int inquiryId) {
		super();
		this.id = id;
		this.payment = payment;
		this.inquiryId = inquiryId;
	}



	public Payment(double payment, Date paymentDate, int inquiryId) {
		
		this.payment = payment;
		this.paymentDate = paymentDate;
		this.inquiryId = inquiryId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	public int getInquiryId() {
		return inquiryId;
	}



	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	} 
	
	
	

}
