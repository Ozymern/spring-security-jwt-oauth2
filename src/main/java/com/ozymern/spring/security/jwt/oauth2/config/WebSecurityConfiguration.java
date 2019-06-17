package com.ozymern.spring.security.jwt.oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ozymern.spring.security.jwt.oauth2.security.UserDetailsServiceImpl;


//anotacion para habilitar la anotacione @secured (securedEnabled=true) y prePostEnabled=true  	@PreAuthorize
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {



	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	

	//autenticamos a los clientes finales
	@Autowired
	public void configurer(AuthenticationManagerBuilder auth) throws Exception {

		// CON JPA
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

	}
	  @Override
	   public void configure(WebSecurity web) throws Exception {
	      web.ignoring();
	   }
	
	   @Bean
	   public PasswordEncoder encoder() {
	      return new BCryptPasswordEncoder();
	   }
	 @Override
	   @Bean
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	   }

}
