package com.spring.order_inventory.service;

import com.spring.order_inventory.dto.LoginRequestDto;
import com.spring.order_inventory.dto.LoginResponseDto;

public interface IAuthService {
	public LoginResponseDto login(LoginRequestDto request);
}
