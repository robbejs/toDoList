package SO.toDoList.model.service;
import SO.toDoList.model.SubTask;
import SO.toDoList.model.entity.Task;
import SO.toDoList.model.dto.TaskDTO;
import SO.toDoList.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository repository;
    private HashMap<Integer, ArrayList<SubTask>> subtasks = new HashMap<>();
    private  int id = 1;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return repository.findAll().stream().map(h -> {
            TaskDTO dto = new TaskDTO();
            dto.setDateAndTimeOfBeheading(h.getDateAndTimeOfBeheading());
            dto.setNaam(h.getNaam());
            dto.setBeschrijving(h.getBeschrijving());
            dto.setId(h.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setNaam(taskDTO.getNaam());
        task.setBeschrijving(taskDTO.getBeschrijving());
        task.setDateAndTimeOfBeheading(taskDTO.getDateAndTimeOfBeheading());
        task.setId(id);
        id++;
        repository.save(task);
    }

    @Override
    public void editTask(TaskDTO taskDTO, int id) {
        Task task = repository.getOne(id+1);
        task.setNaam(taskDTO.getNaam());
        task.setDateAndTimeOfBeheading(taskDTO.getDateAndTimeOfBeheading());
        task.setBeschrijving(taskDTO.getBeschrijving());
        repository.save(task);
    }

    @Override
    public void addSubtask(int id, SubTask subTask){
        if (subtasks.get(id) == null) {
            subtasks.put(id, new ArrayList<>());
        }
        subtasks.get(id).add(subTask);
    }

    @Override
    public void deleteTask(int id) {
        Task task = repository.getOne(id+1);
        repository.delete(task);
    }

    @Override
    public ArrayList<SubTask> returnSubtask(int id){
        return subtasks.get(id);
    }
}
    