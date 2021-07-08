package fr.epsi.b3.c4.mongodb_code.repository;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.b3.c4.mongodb_code.model.Quotation;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;

@Repository
public interface QuotationRepository extends MongoRepository<Quotation, String> {
	
	List<Quotation> findByAuthorLikeIgnoreCase(String author);
	
	List<Quotation> findByQuoteLikeIgnoreCase(String quote);
	
	List<Quotation> findByAuthorLikeIgnoreCaseAndQuoteLikeIgnoreCase(String author, String quote);
	
	List<Quotation> findByWorkLike(String work);

	@Aggregation(pipeline = {"{ $match: { author: '' } }", 
			"{ $group: { _id: '$author', count: { $sum: 1 } } }", 
			"{$project : { value: '$count', _id:0} }"})
	AggregationResults<ResultString> getQuotationWithoutAuthorNumber();
	
	
	@Aggregation(pipeline = {"{$group : {_id:'$author', count:{$sum:1}}}",
			"{$sort:{'count':-1}}",
			"{$limit:1}",
			"{$project : { value:'$_id', count: '$count'}}"})
	AggregationResults<ResultString> getMostQuotedAuthor();
	
	
}