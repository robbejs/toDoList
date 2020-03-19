package SO.toDoList.Controller;
import SO.toDoList.domain.SubTask;
import SO.toDoList.domain.Task;
import SO.toDoList.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class toDoController {
    private int id;
    private final TaskServiceImpl service;

    @Autowired
    public toDoController(TaskServiceImpl service) {
        this.service = service;
    }

    @RequestMapping
    public String navigatie(Model model){
        model.addAttribute("tasks", this.service.getTasks());
        return "tasks";
    }

    @RequestMapping("/tasks")
    public String getTasks(Model model){
        if (service.getTasks().size() == 0){
            model.addAttribute("tasks", "No tasks left to do");
        }else{
            model.addAttribute("tasks", service.getTasks());
        }
        return "tasks";
    }

    @RequestMapping("/tasks/{id}")
    public String showTasks(Model model, @PathVariable("id") int id){
        this.id = id;
        if (service.getTasks().size() <= id || id < 0){
            model.addAttribute("taskDetail",null);
        }else {
            model.addAttribute("taskDetail", service.getTask(id));
            model.addAttribute("subTasks", service.getTask(id).getSubTasks());
        }
        return "showTasks";
    }

    @RequestMapping("/tasks/new")
    public String showAddTask(Model model){
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/tasks/addTask")
    public String addTask(@ModelAttribute @Valid Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addTask";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/edit/{id}")
    public String showEditTask(@PathVariable("id") int id, Model model){
        this.id = id;
        model.addAttribute("task", service.getTask(id));
        return "editTask";
    }

    @PostMapping("/tasks/editTask")
    public String editTask(@ModelAttribute @Valid Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "editTask";
        }
        service.getTask(id).setNaam(task.getNaam());
        service.getTask(id).setDatum(task.getDatum());
        service.getTask(id).setBeschrijving(task.getBeschrijving());
        return "redirect:/tasks/"+id;
    }

    @RequestMapping("/tasks/{id}/sub/create")
    public String showCreateSubTask(@PathVariable("id") int id, Model model){
        this.id = id;
        model.addAttribute("subTask", new SubTask());
        return "createSubTask";
    }

    @PostMapping("/tasks/createSubTask")
    public String createSubTask(@ModelAttribute @Valid SubTask subTask, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "createSubTask";
        }
        service.getTask(id).addSubTask(subTask);
        return "redirect:/tasks/"+id;
    }
}
