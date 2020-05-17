package SO.toDoList;
import SO.toDoList.model.dto.TaskDTO;
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
    public void testAddTaskToTaskService(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0);
        taskDTO.setNaam("wiskunde");
        taskDTO.setDateAndTimeOfBeheading(LocalDateTime.now());
        taskDTO.setBeschrijving("test van wiskunde");


        service.addTask(taskDTO);
        List<TaskDTO> tasks = service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }

}
