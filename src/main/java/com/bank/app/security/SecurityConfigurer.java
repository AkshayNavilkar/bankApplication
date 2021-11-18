package com.bank.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	private CustomUserDetailService customUserDetailsService;

//	@Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//       authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin123").roles("ADMIN")
//       .and()
//       .withUser("user").password("user123").roles("USER");
//    }
//
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//    	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests() .antMatchers("api/transaction/**")
	 * .fullyAuthenticated() .and() .httpBasic(); }
	 */
	 
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//			.antMatchers("/transaction/**").hasAnyRole("USER","ADMIN")
//			.antMatchers("/account/getall").hasAnyRole("ADMIN")
//			.antMatchers("/account/**").hasAnyRole("ADMIN","USER")
//			.antMatchers("/api/**").hasAnyRole("USER")
//			.antMatchers("api/userlogin/**").permitAll()
//			.antMatchers("/api/getallUsers").hasAnyRole("ADMIN")
//			.antMatchers("/api/getallinactiveusers").hasAnyRole("ADMIN")
//			.antMatchers("/api/getallactiveusers").hasAnyRole("ADMIN")
//			.anyRequest()
//			.fullyAuthenticated()
//			.and()
//			.httpBasic();
//	}
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()
				.authorizeRequests()
				.antMatchers("/token").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(customUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests() .anyRequest()
	 * .fullyAuthenticated() .and() .httpBasic(); }
	 */
    
    
    
}
