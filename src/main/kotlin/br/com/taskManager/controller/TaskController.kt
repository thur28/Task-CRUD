package br.com.taskManager.controller;


import br.com.taskManager.model.Task
import br.com.taskManager.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class TaskController {

    @Autowired
    lateinit var repo : TaskRepository

    @GetMapping("/")
    fun redirectToHome(): String {
        return "redirect:/home"
    }


    //this function will open the form
    @GetMapping("/form/register") // Receive GET requests
    fun openRegisForm(model: Model): String {

        val task = Task()

        model.addAttribute("newTask", task)

        return "task-registration-form"
    }

    @PostMapping("/register")
    fun registerTask(task: Task): String{

        println(task)
        repo.save(task)
        return "redirect:/home"
    }

    @GetMapping("/home")
    fun openHome(model: Model): String{

        val task = repo.findAll()
        model.addAttribute("taskView", task)

        return "home"
    }

    @GetMapping("/delete/{id}")
    fun deleteTask(@PathVariable("id") id: Long): String{

        repo.deleteById(id)

        return "redirect:/home"
    }

    @GetMapping("/form/edit/{id}")
    fun openFormEdit(@PathVariable("id") id: Long, model: Model): String{
        val task = repo.findById(id).orElse(null)

        model.addAttribute("taskEdit", task)

        return "task-edit-form"
    }

    @PostMapping("/edit/{id}")
    fun editTask(task: Task, @PathVariable("id") id: Long): String{

        task.id = id
        repo.save(task)

        return "redirect:/home"
    }

}
