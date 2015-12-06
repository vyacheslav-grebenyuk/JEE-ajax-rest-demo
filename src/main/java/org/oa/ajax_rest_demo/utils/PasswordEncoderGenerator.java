package org.oa.ajax_rest_demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

  public static void main(String[] args) {

	int i = 0;
	while (i < 10) {
		String password = "cust";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
		i++;
	}
  }
}
