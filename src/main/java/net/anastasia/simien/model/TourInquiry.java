package net.anastasia.simien.model;

import javax.persistence.Column;                                                                 
import javax.persistence.Entity;                                                                 
                                                            
import javax.persistence.GeneratedValue;                                                         
import javax.persistence.GenerationType;                                                         
import javax.persistence.Id;                                                                     
                                                             
import javax.persistence.Table;                                                                  
                                                                                                 
import com.fasterxml.jackson.annotation.JsonIdentityInfo;                                        
import com.fasterxml.jackson.annotation.ObjectIdGenerators;  
import java.util.Date;
                                                                                                 
@Entity                                                                                          
@Table(name = "tourInguiry")                                                                           
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")        
public class TourInquiry {
    
	@Id                                                                                          
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                          
	private int id;                                                                              
	        
	@Column(name = "name", nullable = false)                                                     
	private String name;      
	
	@Column(name = "surname", nullable = false)                                                     
	private String surname; 
	        
	@Column(name = "email", nullable = false)                                                 
	private String email;   
	
	@Column(name = "days", nullable = false)                                                     
	private Integer days;                                                                         
	        
	@Column(name = "persons", nullable = false)                                                 
	private Integer persons;
	
	@Column(name = "date", nullable = false)                                                     
	private Date date;                                                                         
	        
	@Column(name = "message", nullable = false)                                                 
	private String message;
	
	@Column(name = "tour_price", nullable = false)                                                 
	private Integer tourPrice;
	
	@Column(name = "deposit", nullable = false)                                                 
	private Integer deposit;
	
	public TourInquiry() {
		
	}
	
	public TourInquiry(int id, String name, String surname, String email, Integer days, Integer persons, Date date, String message, Integer tourPrice, Integer deposit) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.days = days;
		this.persons = persons;
		this.date = date;
		this.message = message;
		this.tourPrice = tourPrice;
		this.deposit = deposit;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTourPrice() {
		return tourPrice;
	}

	public void setTourPrice(Integer tourPrice) {
		this.tourPrice = tourPrice;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	
	
	

}
