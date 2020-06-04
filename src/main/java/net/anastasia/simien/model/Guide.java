package net.anastasia.simien.model;

                                                            
import javax.persistence.Column;                                                                 
import javax.persistence.Entity;                                                                 
                                                            
import javax.persistence.GeneratedValue;                                                         
import javax.persistence.GenerationType;                                                         
import javax.persistence.Id;                                                                     
                                                             
import javax.persistence.Table;                                                                  
                                                                                                 
import com.fasterxml.jackson.annotation.JsonIdentityInfo;                                        
import com.fasterxml.jackson.annotation.ObjectIdGenerators;                                      
                                                                                                 
@Entity                                                                                          
@Table(name = "guide")                                                                           
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")        
public class Guide {  

                                                                                                 
	@Id                                                                                          
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                          
	private int id;                                                                              
                                                                                                 
	@Column(name = "name", nullable = false)                                                     
	private String name;                                                                         
                                                                                                 
	@Column(name = "nickname", nullable = false)                                                 
	private String nickname;                                                                     
	                                                                                             
	@Column(name = "password", nullable = false)                                                 
	private String password;                                                                     
	                                                                                             
	@Column(name = "english")                                                                    
	private Boolean english;                                                                     
	                                                                                             
	@Column(name = "french")                                                                     
	private Boolean french;    
	
	@Column(name = "email", nullable = false)                                                 
	private String email;   
	
	@Column(name = "phone", nullable = false)                                                 
	private String phone;   
                                                                                                 
	public Guide() {                                                                             
                                                                                                 
	}                                                                                            
                                                                                                 
                                                                                                 
	public Guide(String name, String nickname, String password, String email, String phone, Boolean english, Boolean french) {
		
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.english = english;
		this.french = french;
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


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean isEnglish() {
		return english;
	}


	public void setEnglish(Boolean english) {
		this.english = english;
	}





	public Boolean isFrench() {
		return french;
	}


	public void setFrench(Boolean french) {
		this.french = french;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


                                                                                                 
                                                                                                 
                                                                                                 
}                                                                                                
