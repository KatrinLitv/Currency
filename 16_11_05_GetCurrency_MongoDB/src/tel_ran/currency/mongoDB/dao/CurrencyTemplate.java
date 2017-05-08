package tel_ran.currency.mongoDB.dao;

import java.time.*;
import java.util.*;

import org.bson.Document;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;

import tel_ran.currency.mongoDB.entity.Currency;
import tel_ran.databases.mongo.MongoConnection;

public class CurrencyTemplate {
	private static final String COLLECTION_NAME = "CurrencyFixer";
	static RestTemplate restTemplate = new RestTemplate();
	static String url = "http://api.fixer.io/";
	private MongoCollection<Document> collection;
	static ObjectMapper mapper = new ObjectMapper();

public CurrencyTemplate(String uriStr, String databaseName, String collectionName){
	MongoConnection mongoConnection = MongoConnection.getMongoCollection(uriStr, databaseName);
	collection = mongoConnection.getDataBase().getCollection(COLLECTION_NAME);
}

public void saveMany(LocalDate date1, LocalDate date2){
	collection.insertMany(getListDocuments(date1,date2));
}

private List<Document> getListDocuments(LocalDate date1, LocalDate date2) {
	List<Document> res = new LinkedList<>();
	for (LocalDate i=date1 ; i.isBefore(date2.plusDays(1)) ;){		
		res.add(getDocument(i));
		System.out.println(i);
		i=i.plusDays(1);
	}
	return res;
}

private Document getDocument(LocalDate date) {
	Document res = new Document();
	String urlDate = url+date;
	Currency json = restTemplate.getForObject(urlDate, Currency.class);
	Map<String,Double> rates=json.getRates();
	rates.put("EUR", 1D);
	
	Date dateId = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	res.put("_id", dateId);
	res.put("year", date.getYear());
	res.put("month", date.getMonthValue());
	res.put("rates", rates);
	return res;
}
}
