package pl.printo3d.waluty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.printo3d.waluty.repository.UserEntity;
import pl.printo3d.waluty.repository.UserRepo;
import pl.printo3d.waluty.repository.UserService;


@Controller
public class LoginController {

  @Autowired
  private UserService us;

  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String logujemy()
  {
    return "login";
  }

  @RequestMapping(value="/register", method = RequestMethod.GET)
  public String registerForm()
  {
    return "register";
  }

  @RequestMapping(value="/register", method = RequestMethod.POST)
  public String rejestrujemy( @RequestParam(value = "username", required = true) String uname,
                              @RequestParam(value = "password", required = true) String passwd,
                              @RequestParam(value = "email", required = true) String email )
  {
    System.out.println("rejestracja..");

    if( org.springframework.util.StringUtils.hasText(uname) &&
        org.springframework.util.StringUtils.hasText(passwd) &&
        org.springframework.util.StringUtils.hasText(email) )
    {
      UserEntity ue = new UserEntity(uname, passwd, email);
      ue.setRole("KIEP");
      us.addUser(ue);
    }
    else
    {
      System.out.println("puste pola!");
      return "register";
    }

    // link aktywacyjny?
    return "login";
  }
}
