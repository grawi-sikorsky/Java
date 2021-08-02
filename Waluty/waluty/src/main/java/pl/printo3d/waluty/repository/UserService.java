package pl.printo3d.waluty.repository;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

  private UserRepo userRepo;

  @Bean
  public PasswordEncoder passwordEncoder() 
  {
      return new BCryptPasswordEncoder();
  }

  @Autowired
  private PasswordEncoder pEncoder;

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo.findByUsername(username);
  }

  public boolean addUser(UserEntity userEntity)
  {
    System.out.println("Adduser..");

    userEntity.setRole("KIEP");
    userEntity.setPasswd(pEncoder.encode(userEntity.getPasswd()));

    //System.out.println("Checking if user exists");
    //UserEntity userFromDB = userRepo.findByUsername(userEntity.username);
  
    userRepo.save(userEntity);
    return true;
  }
}