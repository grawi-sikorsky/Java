package pl.printo3d.waluty.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder pEncoder;


  public UserService(UserRepo userRepo, PasswordEncoder pEncoder) {
    this.userRepo = userRepo;
    this.pEncoder = pEncoder;
  }

  public boolean addUser(UserEntity userEntity)
  {
    System.out.println("Adduser..");

    userEntity.setRole("KIEP");
    userEntity.setPasswd(pEncoder.encode(userEntity.getPasswd()));

    System.out.println("Checking if user exists");
    List<UserEntity> userFromDB = userRepo.findByUsername(userEntity.username);

    if(userFromDB.isEmpty() == true)
    {
      userRepo.save(userEntity);
      return true;
    }
    else
    { 
      for (UserEntity ufdb : userFromDB) 
      {
        System.out.println(ufdb.getUsername());
      }
      return false;
    }
  }
}
