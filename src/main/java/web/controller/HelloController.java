package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/")
	public String printAllUsersWithForm(@ModelAttribute("user") User user, ModelMap modelMap) {
		modelMap.addAttribute("user", user);
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		modelMap.addAttribute("users", usersFromDB);
		return "users";
	}

	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	public String printAllUsersWithNewAdded(@ModelAttribute("user") User user, ModelMap mm, ModelMap modelMap) {
		modelMap.addAttribute("user", user);		//геттеры для формы
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		User misha = new User(name,age,lastName);
		userService.add(misha);
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		mm.addAttribute("users", usersFromDB);
		return "users";
	}



	/* Test-env
	@GetMapping(value = "/users")
	public String printUsers(ModelMap model) {
		List<User> users = new ArrayList<>();
		User user1 = new User("Vasya",35,"Ivanov");
		User user2 = new User("Petya",23,"Petrov");
		userService.add(user2);
		System.out.println(user2);
		users.add(user1);
		users.add(user2);
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		model.addAttribute("users", usersFromDB);
		user2.setAge(50);
		Optional<User> opt = userService.read(1L);
		if (opt.isPresent()) {User testUserForPrintAndDelete = opt.get();
			System.out.println(testUserForPrintAndDelete);
		} else {
			System.out.println("no User with this ID");
		}
		userService.update(user2.getID(), user2);
		Optional<User> opt2 = userService.read(user2.getID());
		if (opt2.isPresent()) {User testUserForPrintAndDelete = opt2.get();
			System.out.println(testUserForPrintAndDelete);
		} else {
			System.out.println("no User with this ID");
		}
//		User testUserforPritnAndDelete = userService.read(1L);
//		userService.update(1L, user2);
		userService.delete(user2);
		return "users";
	}
	*/
}