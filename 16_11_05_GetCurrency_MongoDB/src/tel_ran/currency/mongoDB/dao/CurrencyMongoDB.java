package tel_ran.currency.mongoDB.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import tel_ran.currency.mongoDB.entity.CurrencyMap;
import tel_ran.currency.mongoDB.repo.CurrencyRepository;

public class CurrencyMongoDB {
	@Autowired
	CurrencyRepository currencies;
	
public List<CurrencyMap> getByYear(int year){
	return currencies.findByYear(year);
}

public List<CurrencyMap> getByYearMonth( int year , int month){
	return currencies.findByYearAndMonth(year, month);
}
}
