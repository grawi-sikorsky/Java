package pl.printo3d.test3;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/todos")
  @ResponseBody
  public Integer todos()
  {
    userService.getAllTodos();
    //showAlltodos(mdl)
    return userService.getAllTodosCount();
  }

  @RequestMapping(value="/users", method={RequestMethod.GET, RequestMethod.POST})
  public String showAllUsers(Model mdl)
  {
    Map<UserModel,TodoModel> uidtodo;// = new Map<UserModel,TodoModel>;

    //userService.getAllTodos();
    //userService.getUsersCount();

    //mdl.addAttribute("utodos", userService.getUserTodoMap());
    mdl.addAttribute("todos", userService.getAllTodos());
    mdl.addAttribute("usrcnt", userService.getUsersCount());
    mdl.addAttribute("usrname", userService.getAllUsers());
    
    //redirectAttributes.addAttribute("todoa", userService.getAllTodos());
    return "users";
  }

  @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
  public String showOneUser(@RequestParam Integer uid, Model mdl)
  {
    mdl.addAttribute("user", userService.getUser(uid));
    mdl.addAttribute("utodo", userService.getUserTodos(uid));
    mdl.addAttribute("compl", userService.getUserCompleteTodos(uid));

    return "user";
  }
}
