package pl.printo3d.test3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.User;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  private List<UserModel> users = Arrays.asList(  new UserModel("123", "uname: askdfj;askjdf"),
                                                  new UserModel("122", "uname: asdfff") );

  private WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com/");


  //private User[] jusers;

  public List<UserModel> getAllUsers()
  {
    return users;
  }

  public UserModel getUser(String id)
  {
    return users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
  }

  public void getAlljUsers()
  {
    //User[] jusers = webClient.get().uri("/users")
    //.retrieve()
    //.bodyToMono(User[].class).block();
    //System.out.println( jusers[0].getFullName().toString() );


    //System.out.println(users);
/*
    System.out.println(webClient.get()
    .uri("/users")
    .retrieve();
*/
    //return usr;
  }
  
  public void getTodos()
  {
    webClient.get()
    .uri("/users")
    .retrieve();
  }

  private Mono<ClientResponse> responseMono = webClient.get()
            .uri("/users/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange();

  private Mono<Response> resp = webClient.get()
  .uri("/users/")
  .accept(MediaType.APPLICATION_JSON)
  .retrieve()
  .bodyToMono(Response.class);

    public String getResult() {
        return ">> Result Mono<ClientResponse> = " + responseMono.flatMap(res -> res.bodyToMono(String.class)).block();
    }

    public String getRes()
    {
      return " >> Moje mono: " + resp.flatMap(r -> r.bodyToMono(String.class)).block();
    }

}
