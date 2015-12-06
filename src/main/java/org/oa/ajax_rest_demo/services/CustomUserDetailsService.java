package org.oa.ajax_rest_demo.services;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.UserRoles;
import org.oa.ajax_rest_demo.model.Users;
import org.oa.ajax_rest_demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger LOG = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepo;

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByLogin(username);
		if (user != null) {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

			for (UserRoles	role : user.getRoles()){
				LOG.info("User Role: " + role.getRole().getUserRoleType());
				authorities.add(new SimpleGrantedAuthority(role.getRole().getUserRoleType()));
			}

			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
					true, true, true, authorities);
		} else {
			LOG.error("User not found: " + username);
			throw new UsernameNotFoundException("User not found!");
		}
	}
}
