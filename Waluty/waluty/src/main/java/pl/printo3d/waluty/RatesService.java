package pl.printo3d.waluty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.web.server.PortInUseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.printo3d.waluty.model.RatesModel;

@Service
public class RatesService {

  RatesModel data = new RatesModel();
  LinkedHashMap<String,String> baseCurrencyValues = new LinkedHashMap<>();

  public RatesModel getRatesFromAPI()
  {
    StringBuilder builder = new StringBuilder();
 
    //builder.append("https://openexchangerates.org/api/latest.json?app_id=8011116e5aaa43698b0eb4dc6cb18ba8");
    builder.append("http://printo3d.pl/latest.json");
    
    RestTemplate restTemplate = new RestTemplate();
    data = restTemplate.getForObject(builder.toString(), RatesModel.class);
    
    //data.getRates().entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));

    //System.out.println("ZNALEZIONE KUFAAA" + getExRateFromName(data.getRates(), "AUD"));

    return data;
  }

  public String getExRateFromName(String searchKey)
  {
    return data.getRates().get(searchKey);
  }

  public LinkedHashMap<String,String> findCurrency(String search)
  {
    LinkedHashMap <String,String> o = new LinkedHashMap();
  
    o.put(search.toUpperCase(), data.getRates().get(search.toUpperCase()));
    return o;
  }

  public String getWaluty()
  {
    return data.getRates().entrySet().stream().collect(Collectors.toList()).toString();
  }

  public String getBaseCurrency()
  {
    return data.getBase();
  }

  public String getWalutyBasedOnBaseCurrency()
  {
    Set<HashMap.Entry<String, String>> entries = data.getRates().entrySet();
    BigDecimal jeden = new BigDecimal(1);

    for (HashMap.Entry<String, String> mapEntry : entries) 
    {
      BigDecimal temp = new BigDecimal(mapEntry.getValue());
      
      temp = jeden.divide(temp, 90, RoundingMode.HALF_UP);
      baseCurrencyValues.put(mapEntry.getKey(), temp.toString().substring(0, 20));
    }

    baseCurrencyValues.entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));
  
    return baseCurrencyValues.entrySet().stream().collect(Collectors.toList()).toString();
  }
}