package SO.toDoList.model.service;
import SO.toDoList.model.dto.TaskDTO;
import SO.toDoList.model.SubTask;
import java.util.ArrayList;
import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks();

    void addTask(TaskDTO task);

    void editTask(TaskDTO taskdto, int id);

    void addSubtask(int id , SubTask subTask);

    ArrayList<SubTask> returnSubtask(int id);
}
