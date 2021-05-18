package pl.printo3d.test3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

  @Autowired
  private UserService userService;
  
  @RequestMapping("/users")
  @ResponseBody
  public List<UserModel> showUsers()
  {
    return userService.getAllUsers();
  }

  @RequestMapping("/user/{id}")
  @ResponseBody
  public UserModel getUser(@PathVariable String id)
  {
    return userService.getUser(id);
  }

  @RequestMapping("/todos")
  @ResponseBody
  public Integer todos()
  {
    userService.getAllTodos();
    //showAlltodos(mdl)
    return userService.getAllTodosCount();
  }

  @RequestMapping(value="atest", method={RequestMethod.GET, RequestMethod.POST})
  public String showAlltodos(@RequestParam String reqname, Model mdl, RedirectAttributes redirectAttributes)
  {
    userService.getAllTodos();
    String heja = "Heja" + reqname + "!";
    mdl.addAttribute("vhtml", userService.getAllTodosCount());
    mdl.addAttribute("atr2", heja);
    
    //redirectAttributes.addAttribute("todoa", userService.getAllTodos());
    return "atest";
  }

  public String ListStudents(ModelMap model) 
  { 
      //List<TodoModel> todolist = userService.getAllTodosCount();
        //model.addAttribute("result", list);
      
      return "View Name here"; 
  } 

  public String pickUser(TodoModel vtodo)
  {
    vtodo.getId();
    return vtodo.getUserId();
  }
}
