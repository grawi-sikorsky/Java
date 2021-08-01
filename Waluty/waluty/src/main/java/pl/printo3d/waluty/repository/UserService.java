package pl.printo3d.waluty.repository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepo userRepo;

  
  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }


  public void addUser(UserEntity userEntity)
  {
    System.out.println("adduser..");
    userRepo.save(userEntity);
  }
  
}
