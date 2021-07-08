package fr.epsi.b3.c4.mongodb_code.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	@Id
	private String userId;
	
	private String name;
	private String email;
	private String password;
	private List<Quotation> quotes;
	private List<Quotation> favorites;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Quotation> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<Quotation> quotes) {
		this.quotes = quotes;
	}
	public void addQuote(Quotation quote) {
		this.quotes.add(quote);
	}
	public boolean removeQuote(Quotation quote) {
		return this.quotes.removeIf(e -> e.getQuotationId().equals(quote.getQuotationId()));
	}
	
	
	public List<Quotation> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Quotation> favorites) {
		this.favorites = favorites;
	}
	public boolean addFavorites(Quotation quote) {
		return this.favorites.add(quote);
	}
	public boolean removeFavorites(Quotation quote) {
		return this.favorites.removeIf(e -> e.getQuotationId().equals(quote.getQuotationId()));
	}
	
}
