package pl.printo3d.test3;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

  private static final String TODOS_URI = "https://jsonplaceholder.typicode.com/todos";
  private static final String USERS_URI = "https://jsonplaceholder.typicode.com/users";

  private final List<TodoModel> todos;
  private final List<UserModel> users;

  UserService() {
    // Fetch from 3rd party API; configure fetch
    RestTemplate restTemplate = new RestTemplate();

    // do fetch and map result
    todos = Arrays.asList(Objects.requireNonNull(restTemplate.getForEntity(TODOS_URI, TodoModel[].class).getBody()));
    users = Arrays.asList(Objects.requireNonNull(restTemplate.getForEntity(USERS_URI, UserModel[].class).getBody()));

    // Zdaza sie konekszyn tajmaut - trzeba zrobic jakies zabezpiecznie
  }

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

  public List<TodoModel> getAllTodos() {
    System.out.println("Feczujemy wszystkie TODOS z jsonplaceholder..");
    System.out.printf("...otrzymano %d obiektow.%n", todos.size());

    long completedCount = todos.stream().filter(TodoModel::isCompleted).count();
    System.out.println("COMPLETED TASKS: " + completedCount + " ze wszystkich: " + todos.size());

    return todos;
  }

  public UserModel getUser(Integer uid) {
    return users.get(uid - 1);
  }

  public String getUserCompleteTodos(int uid) {

    List<TodoModel> userTodos = getUserTodos(uid);

    long todoDoneCount = userTodos.stream()
            .filter(TodoModel::isCompleted)
            .count();

    return String.format("Zadania zakonczone : %d / %d", todoDoneCount, userTodos.size());
  }

  // w getUserTodos oraz getUserCompleteTodos jest wykonywany ten sam stream filtrujacy - poprawic
  public List<TodoModel> getUserTodos(int uid) {
    return todos.stream()
            .filter(c -> c.getUserId() == uid)
            .collect(Collectors.toList());
  }

  public Map<Integer,List<TodoModel>> getUserTodoMap() {
    MultiValueMap<Integer, TodoModel> mp = new LinkedMultiValueMap<>();
    for (TodoModel td : todos) {
      mp.add(td.getUserId(), td);
    }
    return mp;
  }
}
