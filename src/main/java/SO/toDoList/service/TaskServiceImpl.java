package SO.toDoList.service;
import SO.toDoList.databank.TaskDB;
import SO.toDoList.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskDB taskDB;

    @Autowired
    public TaskServiceImpl(TaskDB taskDB) {
        this.taskDB = taskDB;
    }

    @Override
    public List<Task> getTasks() {
        return taskDB.getTasks();
    }

    @Override
    public void addTask(Task task) {
        taskDB.addTask(task);
    }

    public Task getTask(int id){
        return taskDB.getTask(id);
    }
}
