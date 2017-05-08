import java.time.LocalDate;
import java.util.*;

public class Currency {
String base; 
Date date;
Map<String,Double> rates;

public Currency(){};
public Currency(String base, Date date, Map<String, Double> rates) {
	super();
	this.base = base;
	this.date = date;
	this.rates = rates;
}

@Override
public String toString() {
	return "Currency [base=" + base + ", date=" + date + ", rates=" + rates + "]";
}

public String getBase() {
	return base;
}

public Date getDate() {
	return date;
}

public Map<String, Double> getRates() {
	return rates;
}

}
