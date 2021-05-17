package pl.printo3d.test3;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class UserService {

  private List<UserModel> users = Arrays.asList(  new UserModel("123", "uname: askdfj;askjdf"),
                                                  new UserModel("122", "uname: asdfff") );

  private WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com/");

  public List<UserModel> getAllUsers()
  {
    return users;
  }

  public UserModel getUser(String id)
  {
    return users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
  }


  public Integer getAllTodosCount() {

    System.out.println("Fetching all TODOS objects through REST..");

    // Fetch from 3rd party API; configure fetch
    RequestHeadersSpec<?> spec = WebClient.create().
        get().uri("https://jsonplaceholder.typicode.com/todos");
    
    long start = System.currentTimeMillis();
    long end;

    // do fetch and map result
    List<TodoModel> todos = spec.retrieve().
        toEntityList(TodoModel.class).block().getBody();

    end = System.currentTimeMillis();
    System.out.println("TIME ELAPSED: " + (end - start) + " ms");
    System.out.println(String.format("...received %d items.", todos.size()));

    start = System.currentTimeMillis();
    int countTrue=0;
    for(TodoModel todo : todos)
    {
      
      if(todo.isCompleted() == true)
      {
        System.out.println("userID: " + todo.getUserId() + ", utitle: " + todo.getTitle() + " is Completed?: " + todo.isCompleted());
        countTrue++;
      }
    }
    System.out.println("COMPLETED TASKS: " + countTrue);

    end = System.currentTimeMillis();
    System.out.println("TIME ELAPSED: " + (end - start) + " ms");

    return todos.size();
  }
}
