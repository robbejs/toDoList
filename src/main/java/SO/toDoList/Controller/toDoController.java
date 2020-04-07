package SO.toDoList.Controller;
import SO.toDoList.domain.SubTask;
import SO.toDoList.domain.Task;
import SO.toDoList.dto.TaskDTO;
import SO.toDoList.service.TaskService;
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
    private TaskService service;

    @Autowired
    public toDoController(TaskServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String getCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/tasks/addTask")
    public String postNewHead(@ModelAttribute @Valid TaskDTO task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTask";
        }
        service.addTask(task);
        return "redirect:/";
    }

    @RequestMapping("/tasks/{id}")
    public String showTasks(Model model, @PathVariable("id") int id){
        this.id = id;
        if (service.getTasks().size() <= id || id < 0){
            model.addAttribute("taskDetail",null);
        }else {
            model.addAttribute("taskDetail", service.getTasks().get(id));
            model.addAttribute("subTasks", service.returnSubtask(id));
        }
        return "showTasks";
    }

    @RequestMapping("/tasks/edit/{id}")
    public String showEditTask(@PathVariable("id") int id, Model model){
        this.id = id;
        model.addAttribute("task", service.getTasks().get(id));
        return "editTask";
    }

    @PostMapping("/tasks/editTask")
    public String editTask(@ModelAttribute @Valid TaskDTO task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "editTask";
        }
        service.editTask(task, id);
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
        service.addSubtask(id, subTask);
        return "redirect:/tasks/"+id;
    }
}
