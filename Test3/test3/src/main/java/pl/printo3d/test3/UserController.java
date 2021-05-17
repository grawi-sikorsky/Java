package pl.printo3d.test3;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class UserController {

  @Autowired
  private UserService userService;
  
  @RequestMapping("/users")
  public List<UserModel> showUsers()
  {
    return userService.getAllUsers();
  }

  @RequestMapping("/user/{id}")
  public UserModel getUser(@PathVariable String id)
  {
    return userService.getUser(id);
  }

  @RequestMapping("/jusers")
  public void getjUsers()
  {
    System.out.print("TROLOROOO");
    userService.getAlljUsers();
    //return userService.getAlljUsers();
  }

  @RequestMapping("/todo")
  public String todo()
  {
    System.out.println("TODOOO TROLOLOOOO!");

    return "<html><body><h1>Java Spring Webclient Example</h1><br><br>" +
            userService.getResult() + "<br>" +
            userService.getRes() + "<br>"+
            "</body></html>";

  }

}
