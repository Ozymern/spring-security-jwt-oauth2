package com.ozymern.spring.security.jwt.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	  @Override
	   public void configure(HttpSecurity  http) throws Exception {
	      http.authorizeRequests()
	      .anyRequest()
	      .authenticated()
	      .and()
	      //cuando no se cuenten con eso privilegios devolvamos un 403 Http status code.
	      .exceptionHandling()
	        .accessDeniedHandler(new OAuth2AccessDeniedHandler())
	        .and()
	      .sessionManagement()
	         .sessionCreationPolicy(SessionCreationPolicy.NEVER);
	         
	   }

}
