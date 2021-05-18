package pl.printo3d.test3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public UserModel getUser(Integer uid)
  {
    UserModel userModel = users.get(uid-1); // user id sa od 1 w gore...

    return userModel;
  }

  public List<TodoModel> getUserCompleteTodos(int uid)
  {
    List<TodoModel> odfiltrowane = 
    todos.stream().filter(c -> c.getUserId()==uid).collect(Collectors.toList());
    
    for (TodoModel tm : odfiltrowane) 
    {
      System.out.println(tm.getId());
      
      //if(todoModel.getUserId() == )
      //mp[]=todoModel.isCompleted()
    }
  
    return odfiltrowane;
  }

  public Map<Integer,TodoModel> getUserTodoMap()
  {
    Map<Integer,TodoModel> mp = new HashMap<Integer,TodoModel>();
    //List<String> tudus = todos.stream().filter(c -> c.getUserId()==uid).collect(Collectors.toList());
    Integer tdcomplete;

    

    for(TodoModel td : todos)
    {
      for(UserModel ur : users)
      {
        if(td.getUserId() == ur.getId())
        {

          System.out.println("ooooooo"+ur.getId());
          mp.put(td.getUserId(), td);
        }
      }
    }
    return mp;
  }

}
