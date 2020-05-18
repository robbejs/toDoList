package SO.toDoList;
import SO.toDoList.model.SubTask;
import SO.toDoList.model.dto.TaskDTO;
import SO.toDoList.model.entity.Task;
import SO.toDoList.model.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    private TaskService service;

    @Test
    public void test1AddTaskToTaskService(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0);
        taskDTO.setNaam("wiskunde");
        taskDTO.setDateAndTimeOfBeheading(LocalDateTime.now());
        taskDTO.setBeschrijving("test van wiskunde");


        service.addTask(taskDTO);

        assertNotNull(service.getTasks().get(0).getNaam());
        assertNotNull(service.getTasks().get(0).getId());
        assertNotNull(service.getTasks().get(0).getDateAndTimeOfBeheading());
        assertNotNull(service.getTasks().get(0).getBeschrijving());
    }

    @Test
    public void test2CreateWorkingTask(){
        Task task = new Task();
        task.setId(0);
        task.setNaam("wiskunde");
        task.setDateAndTimeOfBeheading(LocalDateTime.now());
        task.setBeschrijving("test van wiskunde");

        assertNotNull(task.getId());
        assertNotNull(task.getNaam());
        assertNotNull(task.getDateAndTimeOfBeheading());
        assertNotNull(task.getBeschrijving());
    }

    @Test
    public void test3EditExistingTask(){

        TaskDTO hoofdTask = new TaskDTO();
        hoofdTask.setId(0);
        hoofdTask.setNaam("nederlands");
        hoofdTask.setDateAndTimeOfBeheading(LocalDateTime.now());
        hoofdTask.setBeschrijving("test van nederlands");

        System.out.println(service.getTasks());

        service.editTask(hoofdTask,0);

        assertEquals(hoofdTask.getNaam(),service.getTasks().get(0).getNaam());
        assertEquals(hoofdTask.getBeschrijving(),service.getTasks().get(0).getBeschrijving());
        assertEquals(hoofdTask.getDateAndTimeOfBeheading(),service.getTasks().get(0).getDateAndTimeOfBeheading());
    }

    @Test
    public void test4AddSubtaskToTask(){
        SubTask subTask = new SubTask();

        subTask.setNaam("Wiskunde");
        subTask.setBeschrijving("Taak wiskunde");

        service.addSubtask(0,subTask);

        assertNotNull(service.returnSubtask(0).get(0).getNaam());
        assertNotNull(service.returnSubtask(0).get(0).getBeschrijving());
    }

    @Test
    public void test5DeleteTaskFromTaskService(){

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0);
        taskDTO.setNaam("wiskunde");
        taskDTO.setDateAndTimeOfBeheading(LocalDateTime.now());
        taskDTO.setBeschrijving("test van wiskunde");

        service.addTask(taskDTO);

        service.deleteTask(1);

        assertEquals(1,service.getTasks().size());
    }

    @Test
    public void test6GetTasks(){
        List<TaskDTO> tasks = service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }

    @Test
    public void test7GetSubtasks(){
        List<SubTask> subTasks = service.returnSubtask(0);

        assertNotNull(subTasks);
        assertFalse(service.returnSubtask(0).isEmpty());
        assertEquals(1, service.returnSubtask(0).size());
        SubTask subTask = service.returnSubtask(0).get(0);
        assertNotNull(subTask);
    }
}
