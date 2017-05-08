package tel_ran.currency.api;

public class CurrencyRequest {
String oldCurrName; 
String newCurrName; 
int sum;
String currecyName; 
int year1;
int year2;

//currencies
public CurrencyRequest(){};

//currency
public CurrencyRequest(String currecyName) {
	super();
	this.currecyName = currecyName;
}


//convert
public CurrencyRequest(String oldCurrName, String newCurrName, int sum) {
	super();
	this.oldCurrName = oldCurrName;
	this.newCurrName = newCurrName;
	this.sum = sum;
}

//statisticsM
public CurrencyRequest(String currecyName, int year1) {
	super();
	this.currecyName = currecyName;
	this.year1 = year1;
}

//statisticsY
public CurrencyRequest(String currecyName, int year1, int year2) {
	super();
	this.currecyName = currecyName;
	this.year1 = year1;
	this.year2 = year2;
}

public String getOldCurrName() {
	return oldCurrName;
}

public String getNewCurrName() {
	return newCurrName;
}

public int getSum() {
	return sum;
}

public String getCurrecyName() {
	return currecyName;
}

public int getYear1() {
	return year1;
}

public int getYear2() {
	return year2;
}


}
