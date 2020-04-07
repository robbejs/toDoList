package SO.toDoList.service;
import SO.toDoList.domain.SubTask;
import SO.toDoList.domain.Task;
import SO.toDoList.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks();

    void addTask(TaskDTO task);

    void editTask(TaskDTO taskdto, int id);

    void addSubtask(int id , SubTask subTask);

    ArrayList<SubTask> returnSubtask(int id);
}
