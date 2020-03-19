package com.gpch.login.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpch.login.model.Images;
import com.gpch.login.model.User;
import com.gpch.login.service.ImageService;
import com.gpch.login.service.MailService;
import com.gpch.login.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private MailService mailService;


    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }
    
	@PostMapping("/login")
	public boolean addUser(@RequestBody User user) throws MessagingException {
		if (userService.findByUsernameOrEmail(user)) {
			return false;
		}
		userService.add(user);
		mailService.inviaMail(user.getEmail(), "conferma login", "Login effettuata correttamente");
		return true;
	}
	
	@PostMapping(path = "/modifiche")
	public String addNewUser(@RequestParam String name, @RequestParam String email, @RequestParam String userName,
			String birthday, String lastName,Model model) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setBirthday(birthday);
		n.setLastName(lastName);
		n.setUserName(userName);
		userService.save(n);
		model.addAttribute("userList", userService.findAll());
		return "/grazie";
	}

	
    @GetMapping("/home")
    public String root() {
        return "/home";
    }
    
	@PostMapping("/image")
	public String fileUpload(@RequestParam("file") MultipartFile file,@RequestParam Long id, Model model) { // upload del fils
		User u = userService.getById(id);
		imageService.salvaFile(file);
		model.addAttribute("id", id);
		model.addAttribute("file", file);
		
	return "/grazie";
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) { // servizio di download
		// Load file from database
		Images dbFile = imageService.recuperaFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}



}