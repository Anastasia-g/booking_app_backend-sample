package net.anastasia.simien.auth;


import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http)  throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	      .antMatchers("/api/v1/makepayment/*").permitAll()
	      .antMatchers("/api/v1/makepayment").permitAll()
	      .antMatchers("/api/v1/getdepositamount/*").permitAll()
	      .antMatchers("/api/v1/booking").permitAll()
	      .antMatchers("/api/v1/bookings").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .oauth2ResourceServer().jwt();
	    http.cors();

	    // Send a 401 message to the browser (w/o this, you'll see a blank page)
	    Okta.configureResourceServer401ResponseBody(http);
		//http.authorizeRequests().anyRequest().permitAll();  
	  }

}
