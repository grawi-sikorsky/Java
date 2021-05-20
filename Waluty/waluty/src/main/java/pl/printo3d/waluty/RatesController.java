package pl.printo3d.waluty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RatesController {

  @Autowired
  RatesService ratesService;
  
  // HOME
  @RequestMapping(method={RequestMethod.GET, RequestMethod.POST})
  public String showRates(Model mdl)
  {
    ratesService.getRatesFromAPI();
    //System.out.println(ratesService.getWaluty()); 

    mdl.addAttribute("waluty", ratesService.data.getRates());
    return "home";
  }

}
