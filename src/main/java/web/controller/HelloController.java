package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.persistence.GeneratedValue;
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
	public String printAllUsersWithNewAdded(@ModelAttribute("user") User user, ModelMap modelMap) {
		modelMap.addAttribute("user", user);		//геттеры для формы
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		User userForAdd = new User(name,age,lastName);
		userService.add(userForAdd);
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		modelMap.addAttribute("users", usersFromDB);
		return "users";
	}

	@GetMapping(value = "/delete/{id}")
	public String printAllUsersAfterDelete(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id){
		modelMap.addAttribute("user", user);		//геттеры для формы
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		Optional<User> optionalUser = userService.read(id);
		if (optionalUser.isPresent()) {userService.delete(optionalUser.get());
			} else {
			System.out.println("ERROR when try to delete User with ID=" + id);
		}
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		modelMap.addAttribute("users", usersFromDB);
		return "users";
	}




	@GetMapping(value = "/userchange/{id}")
	public String printFormForUserChange(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id) {
		modelMap.addAttribute("user", user);		//геттеры для формы
		modelMap.addAttribute("IDforChange", id);
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		return "userChangePage";
	}

	@RequestMapping(value = "/useredit/{id}", method = RequestMethod.POST)
	public String printAllUsersWithChanged(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id) {
		modelMap.addAttribute("user", user);		//геттеры для формы
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();

		Optional<User> opt = userService.read(id);
		if (opt.isPresent()) {
			User userFromOpt = opt.get();
			userFromOpt.setName(user.getName());
			userFromOpt.setLastname(user.getLastname());
			userFromOpt.setAge(user.getAge());
			userService.update(id, userFromOpt);
		} else {
			System.out.println("ERROR CHANGE USER: no User with this ID");
		}

		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		modelMap.addAttribute("users", usersFromDB);
		return "users";
	}

	 //Test-env
/*	@GetMapping(value = "/users")
	public String printUsers(ModelMap model) {
//		List<User> users = new ArrayList<>();
////		User user1 = new User("Vasya",35,"Ivanov");
//		User user2 = new User("PetyaTestov",23,"PetrovTestov");
//		userService.add(user2);
//		System.out.println(user2);
////		users.add(user1);
////		users.add(user2);
//		List<User> usersFromDB = new ArrayList<>();
//		usersFromDB = userService.listUsers();
////		model.addAttribute("users", usersFromDB);
//		user2.setAge(50);
//
////		userService.update(user2.getID(), user2);
//		Optional<User> opt2 = userService.read(user2.getID());
//		if (opt2.isPresent()) {User testUserForPrintAndDelete = opt2.get();
//			System.out.println(testUserForPrintAndDelete);
//		} else {
//			System.out.println("no User with this ID");
//		}
////		User testUserforPritnAndDelete = userService.read(1L);
//
//		userService.update(1L, user2);
////		userService.delete(user2);


		User testUserForPrintAndDelete = new User();
		Optional<User> opt = userService.read(1L);
		if (opt.isPresent()) {testUserForPrintAndDelete = opt.get();
			System.out.println(testUserForPrintAndDelete);
		} else {
			System.out.println("no User with this ID");
		}

		testUserForPrintAndDelete.setAge(9);
		testUserForPrintAndDelete.setLastname("test");
		testUserForPrintAndDelete.setName("test");
		userService.update(1L,testUserForPrintAndDelete);


		return "users";
	}
*/
}