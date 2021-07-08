package fr.epsi.b3.c4.mongodb_code.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import fr.epsi.b3.c4.mongodb_code.model.ResultMap;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;
import fr.epsi.b3.c4.mongodb_code.model.User;
import fr.epsi.b3.c4.mongodb_code.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public User create(User user) {
		if(user.getQuotes() == null || user.getFavorites() == null) {
			user.setQuotes(new ArrayList<>());
			user.setFavorites(new ArrayList<>());
		}
		
		return repository.save(user);
	}
	
	public User update(User user) {
		return repository.save(user);
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	public User findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	public AggregationResults<ResultString> getMostFavoredAuthor() {
		return repository.getMostFavoredAuthor();
	}
	
	public AggregationResults<ResultMap> getThreeMostFavoredQuotes() {
		return repository.getThreeMostFavoredQuotes();
	}
	
	public AggregationResults<ResultMap> getBestMember() {
		return repository.getBestMember();
	}
}
