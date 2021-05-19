package pl.printo3d.test5.model;

import org.springframework.security.core.userdetails.UserDetails;

public class UserModel implements UserDetails{

  private String username;
  private String password; // lepiej w tablicy
  private String role;
  
	public UserModel(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
  
}
