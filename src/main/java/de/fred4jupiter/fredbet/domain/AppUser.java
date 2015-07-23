package de.fred4jupiter.fredbet.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
public class AppUser implements UserDetails {

	private static final long serialVersionUID = -2973861561213230375L;

	@Id
	private String id;

	private List<String> roles;

	private String username;

	private String password;

	public AppUser(String username, String password, String... roles) {
		this(username, password, Arrays.asList(roles));
	}

	@PersistenceConstructor
	public AppUser(String username, String password, List<String> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
