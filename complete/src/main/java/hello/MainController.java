package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after
								// Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	private UserRepository userRepository;

	/*
	 * @GetMapping(path = "/add") // Map ONLY GET Requests public @ResponseBody
	 * String addNewUser(@RequestParam String name, @RequestParam String email)
	 * { // @ResponseBody means the returned String is the response, not a view
	 * // name // @RequestParam means it is a parameter from the GET or POST
	 * request
	 * 
	 * User n = new User(); n.setName(name); n.setEmail(email);
	 * userRepository.save(n); return "Saved"; }
	 */

	@PostMapping(value = "/user")
	public ResponseEntity createUser(@RequestBody User user) {

		userRepository.save(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		userRepository.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/user/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody User user) {

		user = userRepository.save(user);

		if (null == user) {
			return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		User user = userRepository.findOne(id);
		if (user == null) {
			return new ResponseEntity("No user found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}

}
