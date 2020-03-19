package SO.toDoList.service;
import SO.toDoList.domain.Task;
import java.util.List;

public interface TaskService {
    public List<Task> getTasks();

    void addTask(Task task);
}
