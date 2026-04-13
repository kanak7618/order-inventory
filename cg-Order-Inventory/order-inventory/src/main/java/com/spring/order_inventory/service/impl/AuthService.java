package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.LoginResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.order_inventory.config.JwtUtil;
import com.spring.order_inventory.dto.LoginRequestDto;
import com.spring.order_inventory.entity.User;
import com.spring.order_inventory.repository.UserRepository;
import com.spring.order_inventory.service.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Override
	public LoginResponseDto login(LoginRequestDto request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("No account found with that email"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Incorrect password");
		}

		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
		return new LoginResponseDto(token, user.getEmail(), user.getRole());
	}
}