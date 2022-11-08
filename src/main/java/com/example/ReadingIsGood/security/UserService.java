package com.example.ReadingIsGood.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private Map<String, User> users = new HashMap<>();

	@PostConstruct
	public void initialize() {
		users.put("readingIsGood", new User("readingIsGood", new BCryptPasswordEncoder().encode("readingIsGood12345"), new ArrayList<>()));
	}

	public User getUserByUsername(String username) {
		return users.get(username);
	}

}
