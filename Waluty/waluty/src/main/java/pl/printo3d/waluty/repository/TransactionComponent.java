package pl.printo3d.waluty.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionComponent {
  private TransRepo transRepo;
  private UserRepo userRepo;

  @Autowired
  public TransactionComponent(TransRepo transRepo, UserRepo tUserRepo) 
  {
    this.transRepo = transRepo;
    this.userRepo = tUserRepo;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void runrunrun()
  {
    TransEntity transik = new TransEntity("var1", "var2","var3","var4");
    transRepo.save(transik);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void updateUserInDB()
  {
    //long id = 1;
    UserEntity usr = new UserEntity("username","pass","email");
    userRepo.save(usr);
  }
  
}
