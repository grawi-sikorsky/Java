package pl.printo3d.waluty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class RatesController {

  @Autowired
  RatesService ratesService;
  
  // HOME
  @RequestMapping(value="/", method={RequestMethod.GET})
  public String showRates(Model mdl)
  {
    ratesService.getRatesFromAPI();
    //System.out.println(ratesService.getWaluty()); 

    mdl.addAttribute("waluty", ratesService.data.getRates());
    mdl.addAttribute("basecurrency", ratesService.getBaseCurrency() );
    mdl.addAttribute("currencies", ratesService.getWalutyBasedOnBaseCurrency() );
    return "home";
  }

  @RequestMapping(value="/", method = {RequestMethod.POST})
  public String searchForCurrency(@RequestParam(value = "q", required = false) String szukaj, Model mdl)
  {
    mdl.addAttribute("basecurrency", ratesService.getBaseCurrency());
    mdl.addAttribute("waluty", ratesService.findCurrency(szukaj));
    System.out.println("SYSYSYSYSYSYYS");
    
    //System.out.println(ratesService.findCurrency("BTC"));
    return "home";
  }

}
