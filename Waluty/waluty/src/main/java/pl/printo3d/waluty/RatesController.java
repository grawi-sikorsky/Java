package pl.printo3d.waluty;

import org.apache.tomcat.util.http.parser.MediaType;
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
  String targetCode="";
  
  // HOME
  @RequestMapping(value="/", method={RequestMethod.GET})
  public String showRates(Model mdl)
  {
    //ratesService.getRatesFromAPI();
    //System.out.println(ratesService.getWaluty()); 

    mdl.addAttribute("waluty", ratesService.rates.getRates());
    mdl.addAttribute("basecurrency", ratesService.getBaseCurrency());
    mdl.addAttribute("currencies", ratesService.getWalutyBasedOnBaseCurrency() );
    mdl.addAttribute("target", ratesService.findExchangeRateForCode("PLN"));
    mdl.addAttribute("exchanged", ratesService.getExchangedRate("PLN"));
    mdl.addAttribute("amountToCalc", "1");
    mdl.addAttribute("amountCalculated", ratesService.CalculateExchange("1", "USD", "PLN"));
    mdl.addAttribute("pickedCurrency", "PLN");

    //mdl.addAttribute("wynik", ratesService.wynik());
    //mdl.addAttribute("currnames", ratesService.getCurrNames());
//    ratesService.getCurrNames();
    return "home";
  }

  @RequestMapping(value="/", method = {RequestMethod.POST}, params = "q")
  public String searchForCurrency(@RequestParam(value = "q", required = false) String szukaj, Model mdl)
  {
    targetCode = szukaj;
    mdl.addAttribute("waluty", ratesService.findCurrency(szukaj));
    mdl.addAttribute("basecurrency", ratesService.getBaseCurrency());
    mdl.addAttribute("currencies", ratesService.getWalutyBasedOnBaseCurrency() );

    //mdl.addAttribute("wynik", ratesService.wynik(swynik));
    System.out.println("REQ1");
    
    //System.out.println(ratesService.findCurrency("BTC"));
    return "home";
  }

  @RequestMapping(value="/", method = {RequestMethod.GET}, params = {"amountToCalc","base","tar"})
  public String jakiswynik( @RequestParam(value = "amountToCalc", required = false) String amountToCalc,
                            @RequestParam(value = "base", required = true) String base,
                            @RequestParam(value = "tar", required = true) String tar,
                            Model mdl)
  {
    mdl.addAttribute("waluty", ratesService.rates.getRates());
    mdl.addAttribute("basecurrency", ratesService.getBaseCurrency());
    mdl.addAttribute("currencies", ratesService.getWalutyBasedOnBaseCurrency() );
    mdl.addAttribute("target", ratesService.findExchangeRateForCode(tar));
    mdl.addAttribute("exchanged", ratesService.getExchangedRate(tar));

    mdl.addAttribute("amountToCalc", amountToCalc);
    mdl.addAttribute("amountCalculated", ratesService.CalculateExchange(amountToCalc, "USD", tar.toUpperCase()));
    mdl.addAttribute("pickedCurrency", tar.toUpperCase());


    System.out.println("REQ2");
    
    //System.out.println(ratesService.findCurrency("BTC"));
    return "home";
  }

}
