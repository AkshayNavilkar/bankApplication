package com.bank.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
       authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin123").roles("ADMIN")
       .and()
       .withUser("user").password("user123").roles("USER");
    }
    
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
    	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests() .antMatchers("api/transaction/**")
	 * .fullyAuthenticated() .and() .httpBasic(); }
	 */
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/transaction/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/account/getall").hasAnyRole("ADMIN")
			.antMatchers("/account/**").hasAnyRole("ADMIN","USER")
			.antMatchers("/api/**").hasAnyRole("USER")
			.antMatchers("api/userlogin/**").permitAll()
			.antMatchers("/api/getallUsers").hasAnyRole("ADMIN")
			.antMatchers("/api/getallinactiveusers").hasAnyRole("ADMIN")
			.antMatchers("/api/getallactiveusers").hasAnyRole("ADMIN")
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.httpBasic();
	}
	
	
	
	


	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests() .anyRequest()
	 * .fullyAuthenticated() .and() .httpBasic(); }
	 */
    
    
    
}
