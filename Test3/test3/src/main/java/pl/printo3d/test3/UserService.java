package pl.printo3d.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class UserService {

  UserService()
  {
    // Fetch from 3rd party API; configure fetch
    RequestHeadersSpec<?> spec = WebClient.create().
    get().uri("https://jsonplaceholder.typicode.com/todos");

    // do fetch and map result
    todos = spec.retrieve().toEntityList(TodoModel.class).block().getBody();

    RequestHeadersSpec<?> uspec = WebClient.create().
    get().uri("https://jsonplaceholder.typicode.com/users");

    users = uspec.retrieve().toEntityList(UserModel.class).block().getBody();

    // Zdaza sie konekszyn tajmaut - trzeba zrobic jakies zabezpiecznie
  }

  private List<TodoModel> todos;
  private List<UserModel> users;

  private WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com/");

  public Integer getUsersCount()
  {
    return users.size();
  }

  public List<UserModel> getAllUsers()
  {
    for(UserModel usr : users)
    {
      System.out.println(usr.getName());
    }
    //users.
    return users;
  }  

  public Integer getAllTodosCount() 
  {
    return todos.size();
  }

  public List<TodoModel> getAllTodos()
  {
    System.out.println("Feczujemy wszystkie TODOS z jsonplaceholder..");
    System.out.println(String.format("...otrzymano %d obiektow.", todos.size()));

    int countTrue=0;
    for(TodoModel todo : todos)
    {
      if(todo.isCompleted() == true)
      {
        System.out.println("userID: " + todo.getUserId() + " TodoID: " + todo.getId() + " is Completed?: " + todo.isCompleted());
        countTrue++;
      }
    }
    System.out.println("COMPLETED TASKS: " + countTrue + " ze wszystkich: " + todos.size());

    return todos;
  }
}
