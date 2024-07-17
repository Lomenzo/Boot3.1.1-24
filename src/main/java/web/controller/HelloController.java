package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Car;
import web.model.User;
import web.service.CarService;
import web.service.CarServiceImp;
import web.service.UserService;
import web.util.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

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

	@RequestMapping(value = "/userapp", method = RequestMethod.POST)
//	public String userAdd(@ModelAttribute("user") User user, ModelMap mm, ModelMap modelMap){
//	@GetMapping(value = "/userapp")
	public String printUsers(@ModelAttribute("user") User user, ModelMap mm, ModelMap modelMap) {
//		ModelAndView modelAndView = new ModelAndView("users", user);
//		modelMap.addAttribute(user);
//		User newUser = new User(user.getName(), user.getAge(),user.getLastname());
//		String name = user.getName();
//		String lastName = user.getLastName();
//		int salary = user.getSalary();

			modelMap.addAttribute("user", user);		//геттеры для формы
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();

//		mm.addAttribute("name", user.getName());
//		mm.addAttribute("age", user.getAge());
//		mm.addAttribute("lastname", user.getLastname());

		User misha = new User(name,age,lastName);
		userService.add(misha);

		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		mm.addAttribute("users", usersFromDB);
		return "users";
	}


	@GetMapping(value = "/userss")
	public String printUserss(@ModelAttribute("user") User user, ModelMap mm, ModelMap modelMap) {
		modelMap.addAttribute("user", user);
		String name = user.getName();
		String lastName = user.getLastname();
		int age = user.getAge();
		mm.addAttribute("name", user.getName());
		mm.addAttribute("age", user.getAge());
		mm.addAttribute("lastname", user.getLastname());
		List<User> usersFromDB = new ArrayList<>();
		usersFromDB = userService.listUsers();
		mm.addAttribute("users", usersFromDB);
		return "users";
	}



	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	public String userOnlyAdd(@ModelAttribute("user") User user, ModelMap modelMap){
		modelMap.addAttribute("name", user.getName());
		modelMap.addAttribute("age", user.getAge());
		modelMap.addAttribute("lastname", user.getLastname());
		return "userAddPage";
	}



/*
	@PostMapping(value = "/userApp")
	public String addUserPostMethod(Model model, @ModelAttribute("userFormer") UserForm userForm, ModelMap modelMap) {

	        model.addAttribute("userFormer", userForm);
	String name = userForm.getName();
	String lastName = userForm.getLastName();
	int salary = userForm.getSalary();
	User newUser = new User(name,lastName,salary);
        userService.saveUser(newUser);
	List<User> usersList = userService.getAllUsers();
        modelMap.addAttribute("users", usersList);
        return "userPage";
*/

//	@RequestMapping(value = "/cars/count={carValue}")
//	public String printCars(@PathVariable int carValue, ModelMap cars) {
//		List<Car> carList = new ArrayList<>();
//		carList.add(new Car("Honda", 883, Colors.red));
//		carList.add(new Car("Mercedes", 600, Colors.grey));
//		carList.add(new Car("Hyundai", 370, Colors.black));
//		carList.add(new Car("KIA", 625, Colors.white));
//		carList.add(new Car("Toyota", 100, Colors.blue));
//		CarService carService = new CarServiceImp(carList);
//		cars.addAttribute("cars", carService.getCarList(carValue));
//		return "car";
//	}

//1. Создайте еще один контроллер, замаппленный на /сагс.
//2. Создайте модель Саг с тремя произвольными полями.
//3. Создайте список из 5 машин.
//4. Создайте сервис с методом, который будет возвращать указанное число машин из созданного списка.
//5. Создайте страницу car.html. Реализуйте создание таблицы с машинами из сервиса с помощью thymeleaf.
//6. При запросе /cars выводить весь список. При запросе /cars?count=2 должен отобразиться список из 2 машин,
//при cars?count=3 - из 3, итд. При cars >= 5 выводить весь список машин.
}