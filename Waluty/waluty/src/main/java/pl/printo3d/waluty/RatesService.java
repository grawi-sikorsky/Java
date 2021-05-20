package pl.printo3d.waluty;

import java.util.HashMap;

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


    System.out.println(data);
    
    data.getRates().entrySet().forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));

    System.out.println("Szukamy KEY: BTC");
    System.out.println("ZNALEZIONE KUFAAA" + getRateFromCur(data.getRates(), "AUD"));

    return data;
  }

  public String getRateFromCur(HashMap<String,String> map, String searchKey)
  {
    return map.get(searchKey);
  }
}
