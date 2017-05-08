package tel_ran.currency.mongoDB.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;

import tel_ran.currency.mongoDB.dao.CurrencyMongoDB;
import tel_ran.currency.mongoDB.dao.OperationsModel;
import tel_ran.security.accounts.AccountData;
import tel_ran.security.accounts.repo.AccountRepository;

import static tel_ran.currency.api.UrlConstants.*;

//http://localhost:8080/currencies
//http://localhost:8080/currency?currecyName=USD
//http://localhost:8080/convert?oldCurrName=USD&newCurrName=ILS&sum=100
//http://localhost:8080/statisticsM?currecyName=USD&year=2015
//http://localhost:8080/statisticsY?currecyName=USD&year1=2011&year2=2015
//localhost:8080/account?account={"name":"broker", "password":"123", "roles":["ROLE_BROKER"]}
@RestController
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class CurrencyService {
@Autowired
AccountRepository accounts;
	
@Autowired
OperationsModel operationsModel;

@Autowired
CurrencyMongoDB currencyMongoDB;

@RequestMapping(value= "account", method=RequestMethod.PUT)
public void addAccount(@RequestBody AccountData account){
	accounts.save(account);
}

@RequestMapping(value = CURRENCIES)
public List<String> currencies(){	
	return operationsModel.currencies();
}
@RequestMapping(value = CURRENCY)
public String currency(String currecyName){
	return operationsModel.currency(currecyName);
}
	
@RequestMapping(value = CONVERT, method=RequestMethod.POST)
public String convert(String oldCurrName, String newCurrName, int sum){
	return operationsModel.convert(oldCurrName, newCurrName, sum);
}

@RequestMapping(value = STATISTICS_M)
public List<String> statisticsM(String currecyName, int year){ 
	return operationsModel.statisticsM(currecyName, year);
}

@RequestMapping(value = STATISTICS_Y)
public List<String> statisticsY(String currecyName, int year1, int year2){
	return operationsModel.statisticsY(currecyName, year1, year2);
}

public static void main(String[] args) {
	SpringApplication.run(CurrencyService.class, args);
}
}
