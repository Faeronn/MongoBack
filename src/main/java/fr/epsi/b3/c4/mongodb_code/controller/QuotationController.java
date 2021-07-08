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

import fr.epsi.b3.c4.mongodb_code.model.Quotation;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;
import fr.epsi.b3.c4.mongodb_code.service.QuotationService;

@CrossOrigin
@RestController
@RequestMapping("/api/quotes")
public class QuotationController {
	
	@Autowired
	private QuotationService service;
	
	@GetMapping
	public List<Quotation> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public Quotation findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	
	@PostMapping()
	public Quotation create(@RequestBody Quotation quotation) {
		return service.create(quotation);
	}
	
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable("id") String id) {
		service.delete(id);
	}
	
	@PutMapping("{id}")
    public Quotation update(@PathVariable("id") String id, @RequestBody Quotation quotation) {
		Quotation q = service.findById(id);
		if(q != null) {
			q.setAuthor(quotation.getAuthor());
			q.setDate(quotation.getDate());
			q.setQuote(quotation.getQuote());
			q.setWork(quotation.getWork());
			return service.update(q);
		}
		else {
			return null;
		}
    }
	
	
	@GetMapping("work/{work}")
	public List<Quotation> findByWork(@PathVariable("work") String work) {
		return service.findByWork(work);
	}
	
	@GetMapping("author/{author}")
	public List<Quotation> findByAuthor(@PathVariable("author") String author) {
		return service.findByAuthor(author);
	}
	
	@GetMapping("quote/{quote}")
	public List<Quotation> findByQuote(@PathVariable("quote") String quote) {
		return service.findByQuote(quote);
	}
	
	@GetMapping("authquote/{author}/{quote}")
	public List<Quotation> findByAuthorAndQuote(@PathVariable("author") String author, @PathVariable("quote") String quote) {
		return service.findByAuthorAndQuote(author, quote);
	}
	
	@GetMapping("quoteNb")
	public List<ResultString> getQuotationWithoutAuthorNumber() {
		return service.getQuotationWithoutAuthorNumber().getMappedResults();
	}
	
	@GetMapping("mostQuotedAuthor")
	public List<ResultString> getMostQuotedAuthor() {
		return service.getMostQuotedAuthor().getMappedResults();
	}
}
