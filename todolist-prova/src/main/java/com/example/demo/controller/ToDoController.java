package com.example.demo.controller;

import com.example.demo.model.Images;
import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.TimeZone;

@Controller
@EnableScheduling
public class ToDoController {
	
    @Autowired
    private UserService userService;
	
    @Autowired
    private ToDoService todoService;
    
    @Autowired
	private ImageServiceImpl imageService;
    
    
	@Autowired
	TaskScheduler scheduler;

    @GetMapping("/todo")
    public String ToDo(Model model){
        List<ToDo>todos = todoService.getAllTodo();
    	//User user = new User();
    	//List<ToDo>todos = user.getActivities();
        model.addAttribute("todos", todos);
        model.addAttribute("todo", new ToDo());
        model.addAttribute("title", "ToDo");
        model.addAttribute("isAdd", true);
        return "todo";

    }

    @PostMapping(value="/save")
    public String save (@ModelAttribute ToDo todo, RedirectAttributes redirectAttributes, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByUserName(auth.getName());	
        ToDo dbtodo = todoService.save(todo);
        user.getActivities().add(dbtodo);
        userService.save(user);
   
        if(dbtodo != null) {
            redirectAttributes.addFlashAttribute("successmessage", "ToDo is saved successfully");
            return "redirect:/todo";
        }else {
            model.addAttribute("errormessage", "ToDo is not save, Please try again");
            model.addAttribute("todo", todo);
            return "todo";
        }
    }

    @GetMapping(value = "/getToDo/{id}")
    public String getTodo(@PathVariable Long id, Model model){
        ToDo todo = todoService.findByID(id);
        List<ToDo>todos = todoService.getAllTodo();
        model.addAttribute("todo", todo);
        model.addAttribute("todos", todos);
        model.addAttribute("title", "Edit ToDo");
        model.addAttribute("isAdd", false);
        return "todo";

    }

    @PostMapping(value="/update")
    public String update (@ModelAttribute ToDo todo, RedirectAttributes redirectAttributes, Model model) {
        ToDo dbtodo = todoService.update(todo);
        if(dbtodo != null) {
            redirectAttributes.addFlashAttribute("successmessage", "ToDo is updated successfully");
            return "redirect:/todo";
        }else {
            model.addAttribute("errormessage", "ToDo is not updated, Please try again");
            model.addAttribute("todo", todo);
            return "todo";
        }
    }
    @GetMapping(value ="/deleteToDo/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        todoService.delete(id);
        redirectAttributes.addFlashAttribute("successmessage", "ToDo is Deleted successfully");

        return "redirect:/todo";
    }
    @GetMapping(value ="/image")
    public String imagePage() {
    	return "/image";
    }
    
    @RequestMapping(value="/image", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file) throws IOException { 
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		Images image = imageService.salvaFile(file);
		userService.uploadImage(user, image);
		modelAndView.setViewName("profilo");
		return modelAndView;
	}
    
    @RequestMapping(value = "/profilo", method = RequestMethod.POST)
    public String profilo(Model model) {   	
    	Images images = new Images();
    	model.addAttribute("authUserImage", Base64.getEncoder().encodeToString(images.getData()));
    	return "/profilo";
    }
    
    
    
    
    
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Images dbFile = imageService.recuperaFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    @ResponseBody
    @GetMapping("/cron")
    public void sendMessageActivity(@RequestBody ToDo todo) {
       
		LocalDateTime dateTime = todo.getExpiration_date();
		int minute = dateTime.getMinute();
		int hours = dateTime.getHour();
		int day = dateTime.getDayOfMonth();
		int month = dateTime.getMonth().getValue();
		String expression = " 0 " + minute  + " " + hours + " " + day + " " + month + " ?";
		System.out.println(expression);
		CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
		scheduler.schedule(todo, trigger);
    }
}