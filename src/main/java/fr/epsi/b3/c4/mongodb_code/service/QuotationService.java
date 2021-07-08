package fr.epsi.b3.c4.mongodb_code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import fr.epsi.b3.c4.mongodb_code.model.Quotation;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;
import fr.epsi.b3.c4.mongodb_code.repository.QuotationRepository;


@Service
public class QuotationService {
	
	@Autowired
	private QuotationRepository repository;
	
	public List<Quotation> findAll() {
		return repository.findAll();
	}
	
	public Quotation findById(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public Quotation create(Quotation category) {
		return repository.save(category);
	}
	
	public Quotation update(Quotation category) {
		return repository.save(category);
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	public AggregationResults<ResultString> getQuotationWithoutAuthorNumber() {
		return repository.getQuotationWithoutAuthorNumber();
	}
	
	public AggregationResults<ResultString> getMostQuotedAuthor() {
		return repository.getMostQuotedAuthor();
	}
	
	public List<Quotation> findByWork(String work) {
		return repository.findByWorkLike(work);
	}
	
	public List<Quotation> findByAuthor(String author) {
		return repository.findByAuthorLikeIgnoreCase(author);
	}
	
	public List<Quotation> findByQuote(String quote) {
		return repository.findByQuoteLikeIgnoreCase(quote);
	}
	
	public List<Quotation> findByAuthorAndQuote(String author, String quote) {
		return repository.findByAuthorLikeIgnoreCaseAndQuoteLikeIgnoreCase(author, quote);
	}
}
