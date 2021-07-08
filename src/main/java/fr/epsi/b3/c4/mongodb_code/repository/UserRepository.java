package fr.epsi.b3.c4.mongodb_code.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.b3.c4.mongodb_code.model.ResultMap;
import fr.epsi.b3.c4.mongodb_code.model.ResultString;
import fr.epsi.b3.c4.mongodb_code.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmailAndPassword(String email, String password);
	
	
	@Aggregation(pipeline = {"{$unwind: '$favorites'}",
		    "{$group: {_id: '$favorites.author', count: { '$sum': 1}}}",
		    "{$sort:{'count':-1}}",
		    "{$limit:1}",
		    "{$project: {value: '$_id', count:'$count'}} "})
	AggregationResults<ResultString> getMostFavoredAuthor();
	
	@Aggregation(pipeline = {"{$unwind: '$favorites'}",
		    "{$group: {_id: '$favorites.quote', count: { '$sum': 1}}}",
		    "{$sort:{'count':-1}}",
		    "{$limit:3}",
		    "{$project: {value: '$_id', count:'$count'}}"})
	AggregationResults<ResultMap> getThreeMostFavoredQuotes();
	
	@Aggregation(pipeline = {"{$project: { value: '$_id', count: { $size:'$quotes' }}}", 
			"{$sort:{'count':-1}}",
			"{$limit:1}"})
	AggregationResults<ResultMap> getBestMember();
}
