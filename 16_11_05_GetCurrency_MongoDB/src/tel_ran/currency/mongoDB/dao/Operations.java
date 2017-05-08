package tel_ran.currency.mongoDB.dao;
import java.util.*;
import java.util.Map.Entry;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.currency.mongoDB.entity.CurrencyMap;

public class Operations {
	Map<String,Double> rates;	
	AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
	CurrencyMongoDB currencyMongo = ctx.getBean(CurrencyMongoDB.class);
	
	public Operations(Map<String, Double> rates) {
		super();
		this.rates = rates;
	}

	public void convert(String args) {
		String params[]= args.split(" ");
		String oldCurrName= params[1];
		String newCurrName=params[2];
		int sum=0;
		try {
			sum = Integer.parseInt(params[3]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		double curOld = rates.get(oldCurrName);
		double curNew = rates.get(newCurrName);
		System.out.println(curNew * sum / curOld);		
	}

	public void currency(String args) {
		String tmp [] = args.split(" ");
		String currecyName = tmp[1];
		System.out.println("Result of your request:");
		System.out.println(currecyName+ " : "+rates.get(currecyName));		
	}

	public void currencies(String args) {
		for (Entry<String, Double> entry : rates.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}		
	}
	
	public void statisticsM(String args){
		String params [] = args.split(" ");
		String currecyName = params[1];
		int year = 0;
		try {
			year = Integer.parseInt(params[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		displayStatisticsMonths(currecyName,year);
	}
	
	private void displayStatisticsMonths(String currecyName, int year) {
		System.out.println("Statistics for " + currecyName +" " +year);
		for (int i=1 ; i<13 ; i++){
			displayOne(currecyName,"month ",i, currencyMongo.getByYearMonth(year, i));
		}			
	}

	private void displayOne(String currecyName,String strMY, int monthORyear, List<CurrencyMap> currencies) {
		double value = 0;
		if (currencies==null){
			System.out.println("Average for "+strMY+monthORyear + ": no data");
			return;
		}
		for (CurrencyMap cur : currencies){
			Map<String, Double> rates = cur.getRates();
			if (rates==null)
				return;
			value += rates.get(currecyName);
		}
		double res = value / currencies.size();
		System.out.println("Average for "+strMY+monthORyear + ": " + res);
	}

	public void statisticsY(String args){
		String params [] = args.split(" ");
		String currecyName = params[1];
		int year1 = 0;
		int year2 = 0;
		try {
			year1 = Integer.parseInt(params[2]);
			year2 = Integer.parseInt(params[3]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		displayStatisticsYears(currecyName,year1,year2);
	}

	private void displayStatisticsYears(String currecyName, int year1, int year2) {
		System.out.println("Statistics for " + currecyName);
		for (int i=year1 ; i<=year2 ; i++){
			displayOne(currecyName, "year ", i, currencyMongo.getByYear(i));
		}
	}
}
