package SO.toDoList.databank;
import SO.toDoList.domain.SubTask;
import SO.toDoList.domain.Task;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TaskDB {
    private ArrayList<Task> tasks;
    private Date d1 = new Date();

    public TaskDB(){
        tasks = new ArrayList<>();
        Task taak = new Task("Wiskunde", LocalDateTime.now(),"Indienen portfolio");
        Task taak1 = new Task("Frans", LocalDateTime.now(),"Test WW");
        Task taak2 = new Task("Nederlands", LocalDateTime.now(),"Taak boek");
        taak.setId(0);
        taak1.setId(1);
        taak2.setId(2);
        taak.addSubTask(new SubTask("test","test"));
        tasks.add(taak);
        tasks.add(taak1);
        tasks.add(taak2);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void addTask(Task task){
        task.setId(this.tasks.size());
        this.tasks.add(task);
    }

    public Task getTask(int id){
        return tasks.get(id);
    }
}
