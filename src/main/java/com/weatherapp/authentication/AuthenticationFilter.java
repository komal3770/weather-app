package com.weatherapp.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter{

	List<AntPathRequestMatcher> permitURLsMatchers;
    
	public AuthenticationFilter(String permitURLs) {
		super();
		if (permitURLs != null && !permitURLs.isEmpty()){
			permitURLsMatchers = new ArrayList<AntPathRequestMatcher>();
			if (permitURLs.contains(",")){
				for (String permitURL: permitURLs.split(",")){
					permitURLsMatchers.add(new AntPathRequestMatcher(permitURL));
				}
				
			} else {
				permitURLsMatchers.add(new AntPathRequestMatcher(permitURLs));
			}
		}
		
	}


	protected boolean hasMatched(HttpServletRequest request){
		System.out.println("permitURLsMatchers "+permitURLsMatchers.isEmpty());
		if (permitURLsMatchers != null && !permitURLsMatchers.isEmpty()){
			for (AntPathRequestMatcher antPathRequestMatcher: permitURLsMatchers){
				System.out.println(request);
				if (antPathRequestMatcher.matches(request)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if ( (!hasMatched(request))){
			System.out.println(request.getUserPrincipal()==null);
			System.out.println(SecurityContextHolder.getContext().getAuthentication()==null);
			if(SecurityContextHolder.getContext().getAuthentication()==null){
				response.getOutputStream().write("Unauthorized User".getBytes());
			}
			else{
				filterChain.doFilter(request, response);
			}
		}
		else{
			filterChain.doFilter(request, response);
		}
	}

}
