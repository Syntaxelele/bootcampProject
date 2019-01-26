package bootcamp.project.controllers;

import bootcamp.project.users.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterAndLogController {


    @GetMapping("/RegView")
    public String Registerer(User user) {
        return "start";

    }
    @PostMapping("/RegView")
    public String Register(User user, @RequestParam(name = "Reg") String button) {

return"start";
}

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bootcamp.project.repo.UserRepo;
import bootcamp.project.users.Student;
import bootcamp.project.users.User;

@Controller
public class RegisterAndLogController {
	
	//private ArrayList<User> usersList = new ArrayList<>();
	
	@Autowired
	UserRepo userRepo;
	//StudentRepo studentRepo;
	
	
	
	
	@GetMapping("/testingStudentAuth")
	public String testingAuthStudent(User user, String button) {

		//if(button.equals("login"))
	//	User user = new User("Marta", "Apina", "marta", "123", 2, "marta@somewhere.com");
		
		
		
		User findbyNameAndPassw = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(findbyNameAndPassw == null)
		{
			System.out.println("User not found");
			return "index";
		}
		else
		{
			System.out.println(findbyNameAndPassw.getUsername());
			return "showAllUsers";
		}
		
	}

	@GetMapping("/showAllUsers")
	public String showAllStudentsToView(Model model) {
		//User u1 = new User("Janis", "Berzins", "Janis", "password", 2, "janis@somewhere.com");
		//User u2 = new User("Marta", "Apina", "marta", "123", 2, "marta@somewhere.com");
		//userRepo.save(u1);
		//userRepo.save(u2);
		System.out.println("dsadasdasdasd");
		
		Iterable<User> userFromDB = userRepo.findAll();
		model.addAttribute("allUsers", userFromDB);
		return "showAllUsers";

	}
	
	
	
	

	
	//SHOW USER BY NAME
		@GetMapping("/showUserByName")
		public String showUserByName(
				@RequestParam(name = "username", defaultValue = "Janis", required = false) String username, Model model) {
			//Iterable<User> userResult = userRepo.findByName(username);
		//	model.addAttribute("allUsers", userResult);
			return "showAllUsers";
		}

	@GetMapping("/")
	public String createNewStudent(User user) {

		return "indexView";
	}
	/*@PostMapping("/")
	public String registerOrLogin(User user) {
		
	}*/
	//new controller

}
