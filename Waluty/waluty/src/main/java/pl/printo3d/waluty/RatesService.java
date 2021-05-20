package pl.printo3d.waluty;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import pl.printo3d.waluty.model.RatesModel;

@Service
public class RatesService {

  public RatesModel getRatesFromAPI()
  {
    RatesModel rm = new RatesModel();
    StringBuilder builder = new StringBuilder();
    
    //RequestHeadersSpec<?> spec = WebClient.create().get().uri("https://openexchangerates.org/api/latest.json?app_id=8011116e5aaa43698b0eb4dc6cb18ba8");

    builder.append("https://openexchangerates.org/api/latest.json?app_id=8011116e5aaa43698b0eb4dc6cb18ba8");
    
    RestTemplate restTemplate = new RestTemplate();
    RatesModel rates = restTemplate.getForObject(builder.toString(), RatesModel.class);

    //rm = spec.retrieve().toEntity(RatesModel.class).block().getBody();

    System.out.println(rates);

    return rates;
  }  
}
