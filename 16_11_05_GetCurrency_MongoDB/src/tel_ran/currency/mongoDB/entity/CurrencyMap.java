package tel_ran.currency.mongoDB.entity;

import java.time.*;
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="CurrencyFixer")
public class CurrencyMap {
@Id
LocalDate date;
@Indexed
int year;
@Indexed
int month;
Map<String,Double> rates;

public CurrencyMap(){};
public CurrencyMap(LocalDate date, Map<String, Double> rates) {
	super();
	this.date = date;
	this.year = date.getYear();
	this.month = date.getMonthValue();//1-12
	this.rates = rates;
}
public LocalDate getDate() {
	return date;
}
public int getYear() {
	return year;
}
public int getMonth() {
	return month;
}
public Map<String, Double> getRates() {
	return rates;
}
@Override
public String toString() {
	return "CurrencyMap [date=" + date + ", rates=" + rates + "]";
}


}
