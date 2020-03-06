//package com.student;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Autowired
////    CustomUserDetailsService customUserDetailsService;
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	  auth.inMemoryAuthentication().withUser("mkyong").password("{noop}123456").roles("USER");
//	  auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
//	  auth.inMemoryAuthentication().withUser("dba").password("{noop}123456").roles("DBA");
////	  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());	
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////	  http.authorizeRequests()
////		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
////		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
////		.and()
////		.formLogin();
//		
//		http.httpBasic()
//        .and()
//        .authorizeRequests()
//        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//        .and()
//        .csrf().disable()
//        .formLogin().disable();
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/h2-console/**");
//	}
//}