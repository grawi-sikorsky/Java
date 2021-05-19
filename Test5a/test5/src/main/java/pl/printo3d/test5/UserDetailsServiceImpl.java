package pl.printo3d.test5;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
  {
    return null;
  }

  
}
