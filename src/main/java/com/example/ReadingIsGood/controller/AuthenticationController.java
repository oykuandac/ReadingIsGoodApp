package com.example.ReadingIsGood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReadingIsGood.security.AuthenticationRequest;
import com.example.ReadingIsGood.security.JwtUserDetailsService;
import com.example.ReadingIsGood.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public String creteToken(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Invalid username or password", ex);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		return jwtUtil.generateToken(userDetails);
	}
}
