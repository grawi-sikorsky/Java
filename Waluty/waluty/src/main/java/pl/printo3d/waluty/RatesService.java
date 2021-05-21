package pl.printo3d.waluty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.printo3d.waluty.model.RatesModel;

@Service
public class RatesService {

  RatesModel data = new RatesModel();

  public RatesModel getRatesFromAPI()
  {
    StringBuilder builder = new StringBuilder();
 
    //builder.append("https://openexchangerates.org/api/latest.json?app_id=8011116e5aaa43698b0eb4dc6cb18ba8");
    builder.append("http://printo3d.pl/latest.json");
    
    RestTemplate restTemplate = new RestTemplate();
    data = restTemplate.getForObject(builder.toString(), RatesModel.class);
    
    data.getRates().entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));

    System.out.println("Szukamy KEY: BTC");
    System.out.println("ZNALEZIONE KUFAAA" + getRateFromCur(data.getRates(), "AUD"));

    return data;
  }

  public String getRateFromCur(HashMap<String,String> map, String searchKey)
  {
    System.out.println("FOUND" + data.getRates().get(searchKey));
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

  public String getFilteredRates(String lookfor)
  {
    return "a";
  }

  public String getBaseCurrency()
  {
    return data.getBase();
  }
}