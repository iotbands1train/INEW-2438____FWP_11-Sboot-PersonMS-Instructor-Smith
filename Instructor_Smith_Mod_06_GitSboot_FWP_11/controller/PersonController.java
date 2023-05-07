package springmvc.controller;
 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.ModelAndView;

import springmvc.dao.RandomPerson;
import springmvc.model.Admin;
import springmvc.model.Person;
import springmvc.service.AdminService;
import springmvc.service.PersonService;
 
@Controller
public class PersonController {

	private static long start;
	private static double timeSecs;

	public static long start() {
		start = System.currentTimeMillis();
		return start;
	}

	public static double end() {
		timeSecs = (System.currentTimeMillis() - start) * .001;
		String time = (String.format("%.2f", timeSecs)).trim().strip();
		timeSecs = Double.parseDouble(time);
		return timeSecs;
	}

	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/")
	private ModelAndView load(Model model) {
		ModelAndView modelAndView = new ModelAndView("login");
		List<Person> persons = personService.getPeopleDBList();
		if (persons == null || persons.size() < 1)
			personService.create(
					new Person(1L, "Instructor", "Smith", "1 Main St.", "Htown", "Tx", "77001", "713-713-1000"));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		model.addAttribute("login", new Admin());

		List<Admin> admins = adminService.getPeopleDBList();
		Admin lastAdmin = admins.get(admins.size() - 1);
		modelAndView.addObject("lastUser", lastAdmin.getUser());
		modelAndView.addObject("lastPass", lastAdmin.getPass());
		return modelAndView;
	}

	@RequestMapping(value = "batch", method = RequestMethod.GET)
	public ModelAndView batchPage() {
		start();
		ModelAndView modelAndView = new ModelAndView("batchPage");
		int amount = 100;
		modelAndView.addObject("amount", amount);
		return modelAndView;
	}

	@RequestMapping(value = "batch", method = RequestMethod.POST)
	public ModelAndView batchingPerson(@RequestParam int num) {
		ModelAndView modelAndView = new ModelAndView("view");
		personService.batch(num);
		List<Person> persons = personService.getPeopleDBList();
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "Batch");
		return modelAndView;
	}

	@RequestMapping(value = "clear")
	private ModelAndView clear() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		personService.deleteAll();
		List<Person> persons = personService.getPeopleDBList();
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ClearALL");
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	}

	@RequestMapping(value = "home")
	private ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("admin", new Admin());
		modelAndView.addObject("size", adminService.count());
		List<Admin> admins = adminService.getPeopleDBList();
		Admin lastAdmin = admins.get(admins.size() - 1);
		modelAndView.addObject("lastUser", lastAdmin.getUser());
		modelAndView.addObject("lastPass", lastAdmin.getPass());
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	}

	@RequestMapping(value = "login")
	private ModelAndView login(@RequestParam("user") String user, @RequestParam("pass") String pass) {
		boolean isAdmin = adminService.findByUserPass(user, pass);
		ModelAndView mv = null;
		Admin admin = null;
		List<Person> db = personService.getPeopleDBList();
		long lastID = db.get(db.size() - 1).getId();
		if (isAdmin) {
			admin = adminService.getAdminByUser(user);
			mv = new ModelAndView("home");
		} else {
			admin = new Admin();
			mv = new ModelAndView("/");
		}
		mv.addObject("lastID", lastID);
		return mv;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView addPersonPage() {
		ModelAndView modelAndView = new ModelAndView("create");
		modelAndView.addObject("person", RandomPerson.randomPerson()); 
		boolean isNew = true;
		modelAndView.addObject("isNew", isNew);
		List<Person> db = personService.getPeopleDBList();
		modelAndView.addObject("listPersons", db);
		long lastID = db.get(db.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	} 
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView addingPerson(@ModelAttribute Person person  ) {
 
		ModelAndView modelAndView = new ModelAndView("view");
		start(); 
		personService.create(person);
		
		List<Person> persons = personService.getPeopleDBList();
		modelAndView.addObject("person",person);
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "Create");
		return modelAndView;
	}

	@RequestMapping(value = "read")
	public ModelAndView listOfPersons() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	}  
	@RequestMapping(value = "byFname", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDFname() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getFname));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	}
	@RequestMapping(value = "byLname", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDLname() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getLname));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byStreet", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDStreet() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getStreet));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byCity", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDCity() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getCity));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byState", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDState() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getState));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byZip", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDZip() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getZip));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byPhone", method = RequestMethod.GET)
	public ModelAndView listOfPersonsIDPhone() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getPhone));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	} 
	@RequestMapping(value = "byID", method = RequestMethod.GET)
	public ModelAndView listOfPersonsID() {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		List<Person> persons = personService.getPeopleDBList();
		Collections.sort(persons, Comparator.comparing(Person::getId));
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "ReadALL");
		return modelAndView;
	}  
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public ModelAndView editPersonPage(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("edit");
		Person person = personService.getPerson(id);
		modelAndView.addObject("person", person);
		return modelAndView;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView edditingPerson(@ModelAttribute Person person) {
		start();
		ModelAndView modelAndView = new ModelAndView("view");
		personService.update(person);
		List<Person> persons = personService.getPeopleDBList();
		modelAndView.addObject("person", new Person());
		modelAndView.addObject("listPersons", persons);
		modelAndView.addObject("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "Update");
		return modelAndView;
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView deletePersonPage(@RequestParam Long id,Model m) {

		ModelAndView modelAndView = new ModelAndView("view");
		Person person = personService.getPerson(id);
		if (person != null)
			deletePerson(person,m);
		else
			modelAndView.addObject("person", new Person(null));
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String  deletePerson(@ModelAttribute Person person,Model modelAndView) {
		start(); 
		personService.delete(person.getId());
		List<Person> persons = personService.getPeopleDBList();
		if(persons.size()<1)personService.create(new Person(null));
		modelAndView.addAttribute("person", new Person());
		modelAndView.addAttribute("listPersons", persons);
		modelAndView.addAttribute("size", persons.size());
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addAttribute("lastID", lastID);
		end();
		modelAndView.addAttribute("time", timeSecs);
		modelAndView.addAttribute("type", "Delete");
		 return "redirect:/read";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView searchPage() {
		ModelAndView modelAndView = new ModelAndView("searchPage");
		long id = 0L;
		modelAndView.addObject("id", id);
		List<Person> persons = personService.getPeopleDBList();
		long lastID = persons.get(persons.size() - 1).getId();
		modelAndView.addObject("lastID", lastID);
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ModelAndView searchingPerson(@RequestParam Long id) {
		start();
		if (id == null)
			id = 0L;
		ModelAndView modelAndView = new ModelAndView("search");
		Person p = personService.getPerson(id);
		if (p == null || id == null) {
			modelAndView.addObject("person", p);
			modelAndView.addObject("id", 0L);
		} else {
			modelAndView.addObject("person", p);
			modelAndView.addObject("id", p.getId());
		}
		end();
		modelAndView.addObject("time", timeSecs);
		modelAndView.addObject("type", "Search");
		int randomImageNum = new Random().nextInt(1, 8);
		modelAndView.addObject("randomImageNum", randomImageNum);
		return modelAndView;
	}

}