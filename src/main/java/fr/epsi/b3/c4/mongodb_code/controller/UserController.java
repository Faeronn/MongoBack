package fr.epsi.b3.c4.mongodb_code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.b3.c4.mongodb_code.model.LoginForm;
import fr.epsi.b3.c4.mongodb_code.model.Quotation;
import fr.epsi.b3.c4.mongodb_code.model.ResultMap;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;
import fr.epsi.b3.c4.mongodb_code.model.User;
import fr.epsi.b3.c4.mongodb_code.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public User findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	
	@PostMapping
	public User create(@RequestBody User user) {
		return service.create(user);
	}
	
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable("id") String id) {
		service.delete(id);
	}
	
	@PutMapping("{id}")
    public User update(@PathVariable("id") String id, @RequestBody User user) {
		User u = service.findById(id);
		if(u != null) {
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setFavorites(user.getFavorites());
			u.setQuotes(user.getQuotes());
			
			return service.update(u);
		}
		else {
			return null;
		}
    }
	
	@CrossOrigin
	@PutMapping("quote/{id}")
    public User addQuote(@PathVariable("id") String id, @RequestBody Quotation quotation) {
		User u = service.findById(id);
		if(u != null) {
			u.addQuote(quotation);
			
			return service.update(u);
		}
		else {
			return null;
		}
    }
	
	@CrossOrigin
	@DeleteMapping("quote/{id}")
    public User removeQuote(@PathVariable("id") String id, @RequestBody Quotation quotation) {
		User u = service.findById(id);
		if(u != null) {
			u.removeQuote(quotation);
			
			return service.update(u);
		}
		else {
			return null;
		}
    }
	
	@CrossOrigin
	@PutMapping("favorite/{id}")
    public User addFavorite(@PathVariable("id") String id, @RequestBody Quotation quotation) {
		User u = service.findById(id);
		if(u != null) {
			u.addFavorites(quotation);
			
			return service.update(u);
		}
		else {
			return null;
		}
    }
	
	@CrossOrigin
	@DeleteMapping("favorite/{id}")
    public User deleteFavorite(@PathVariable("id") String id, @RequestBody Quotation quotation) {
		User u = service.findById(id);
		if(u != null) {
			u.removeFavorites(quotation);
			
			return service.update(u);
		}
		else {
			return null;
		}
    }
	
	@PostMapping("login")
    public User login(@RequestBody LoginForm login) {
		User u = service.findByEmailAndPassword(login.getEmail(), login.getPass());
		
		if(u != null) {
			return u;
		}
		else {
			return null;
		}
    }
	
	@GetMapping("mostFavoredAuthor")
	public List<ResultString> getMostFavoredAuthor() {
		return service.getMostFavoredAuthor().getMappedResults();
	}
	
	@GetMapping("mostFavoredQuotes")
	public List<ResultMap> getThreeMostFavoredQuotes() {
		return service.getThreeMostFavoredQuotes().getMappedResults();
	}
	
	@GetMapping("bestMember")
	public List<ResultMap> getBestMember() {
		List<ResultMap> m = service.getBestMember().getMappedResults();
		ResultMap r = m.get(0);
		r.setValue(service.findById(r.getValue()).getName());
		return m;
	}
	
	
}
