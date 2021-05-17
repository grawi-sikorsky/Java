package pl.printo3d.test3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

  @RequestMapping("/todos")
  public Integer todos()
  {
    return userService.getAllTodosCount();
  }

  @RequestMapping("/")
  public String pickUser(TodoModel vtodo)
  {
    vtodo.getId();
    return "s";
  }
}
