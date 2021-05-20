package pl.printo3d.waluty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RatesController {

  @Autowired
  RatesService ratesService;
  
  @RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
  public String showRates()
  {
    ratesService.getRatesFromAPI();
    return "home";
  }

}
