package pl.printo3d.waluty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.printo3d.waluty.model.CurNamesModel;
import pl.printo3d.waluty.model.RatesModel;

@Service
public class RatesService {

  RatesModel rates = new RatesModel();         // Rate Model class
  CurNamesModel names = new CurNamesModel();  // Currencies names Model class
  LinkedHashMap<String,String> baseCurrencyValues = new LinkedHashMap<>();  // Converted prices based on picked currency (only USD - fri api..)

  public void getRatesFromAPI()
  {
    //StringBuilder builder = new StringBuilder();
 
    //builder.append("https://openexchangerates.org/api/latest.json?app_id=8011116e5aaa43698b0eb4dc6cb18ba8");
    //builder.append("http://printo3d.pl/latest.json");
    
    RestTemplate restTemplate = new RestTemplate();
    RestTemplate rt = new RestTemplate(); // ??
    rates = restTemplate.getForObject("http://printo3d.pl/latest.json", RatesModel.class);
    names = rt.getForObject("http://printo3d.pl/currencies.json", CurNamesModel.class);
    
    //names.getCodenames().entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));

    //return rates;
  }

  public String getExRateFromName(String searchKey)
  {
    return rates.getRates().get(searchKey);
  }

  public LinkedHashMap<String,String> findCurrency(String search)
  {
    LinkedHashMap <String,String> o = new LinkedHashMap();
  
    o.put(search.toUpperCase(), rates.getRates().get(search.toUpperCase()));
    return o;
  }

  public String getWaluty()
  {
    return rates.getRates().entrySet().stream().collect(Collectors.toList()).toString();
  }

  public String getCurrNames()
  {
    names.getCode().entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));
    return names.getCode().entrySet().stream().collect(Collectors.toList()).toString();
  }

  public String getBaseCurrency()
  {
    return rates.getBase();
  }

  public LinkedHashMap<String,String> getWalutyBasedOnBaseCurrency()
  {
    Set<HashMap.Entry<String, String>> entries = rates.getRates().entrySet();
    BigDecimal jeden = new BigDecimal(1);

    for (HashMap.Entry<String, String> mapEntry : entries) 
    {
      BigDecimal temp = new BigDecimal(mapEntry.getValue());
      
      temp = jeden.divide(temp, 8, RoundingMode.HALF_EVEN);

      baseCurrencyValues.put(mapEntry.getKey(), temp.toPlainString());
    }

    baseCurrencyValues.entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));
  
    return baseCurrencyValues;
  }

  public String wynik(String swynik)
  {

    return swynik;
  }
}