package com.weatherapp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.weatherapp.authentication.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final static String permitURLS = "/,/register,/signin,/resources/**,/logout";
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/resources/**","/","/register","/signin,/logout").permitAll()
		.anyRequest().authenticated()
		.and()
        .exceptionHandling()
        .and()
        //.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .permitAll()
        .loginProcessingUrl("/signin")
        .usernameParameter("email")
        .passwordParameter("password")
        .successHandler((HttpServletRequest request, HttpServletResponse response,Authentication authentication) -> {
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().print("Succefully LoggedIn");
		})
        .failureHandler((HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) -> {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print("Authorization Failed");
		})
        .and()
        .logout()
        .permitAll();
	}

	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordencoder(){
		return  new BCryptPasswordEncoder();//uses a salt to generate the encrypted password
	}
	
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
	
	@Bean
    public AuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new AuthenticationFilter(permitURLS);
    }
}
