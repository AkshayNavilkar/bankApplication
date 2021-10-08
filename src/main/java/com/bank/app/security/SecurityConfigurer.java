package com.bank.app.security;

import com.bank.app.impl.AccountServiceImpl;
import com.bank.app.service.IAccountService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
       authenticationManagerBuilder.inMemoryAuthentication().withUser("username1").password("password1").roles("ADMIN");
       authenticationManagerBuilder.inMemoryAuthentication().withUser("username2").password("password2").roles("USER");
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
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/**")
			.hasAnyRole("ADMIN")
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
