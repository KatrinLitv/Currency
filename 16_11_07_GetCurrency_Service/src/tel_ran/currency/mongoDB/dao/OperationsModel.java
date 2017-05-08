package tel_ran.currency.mongoDB.dao;
import java.util.*;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import tel_ran.currency.mongoDB.entity.CurrencyMap;

public class OperationsModel {
RestTemplate restTemplate = new RestTemplate();
String url = "http://api.fixer.io/latest";
Map<String,Double> rates;

@Autowired
CurrencyMongoDB currencyMongo;
	
	public OperationsModel() {
		CurrencyMap json = restTemplate.getForObject(url, CurrencyMap.class);
		rates = json.getRates();
		rates.put("EUR", 1D);
	}

	public String convert(String oldCurrName, String newCurrName, int sum) {
		double curOld = rates.get(oldCurrName);
		double curNew = rates.get(newCurrName);
		return "" +sum+ " "+ oldCurrName +" = " +(curNew * sum / curOld)+" "+newCurrName;		
	}

	public String currency(String currecyName) {
		return "Result of your request for "+currecyName+": " + rates.get(currecyName);		
	}

	public List<String> currencies() {
		List<String> res = new LinkedList<>();
		for (Entry<String, Double> entry : rates.entrySet()){
			//System.out.println(entry.getKey() + " : " + entry.getValue());
			res.add(entry.getKey() + " : " + entry.getValue());
		}	
		return res;
	}
	
	public List<String> statisticsM(String currecyName, int year){
		return displayStatisticsMonths(currecyName,year);
	}
	
	private List<String> displayStatisticsMonths(String currecyName, int year) {
		List<String> res = new LinkedList<>();
		res.add("Statistics for " + currecyName +" " +year);
		for (int i=1 ; i<13 ; i++){
			List<CurrencyMap> resList = currencyMongo.getByYearMonth(year, i);
			if (resList!=null)
				res.add(displayOne(currecyName,"month ",i, resList));
		}
		return res;
	}

	private String displayOne(String currecyName,String strMY, int monthORyear, List<CurrencyMap> currencies) {
		double value = 0;
		if (currencies==null){
			return "Average for "+strMY+monthORyear + ": no data";
		}
		for (CurrencyMap cur : currencies){
			Map<String, Double> rates = cur.getRates();
			if (rates==null)
				return "no rates found";
			value += rates.get(currecyName);
		}
		double res = value / currencies.size();
		return "Average for "+strMY+monthORyear + ": " + res;
	}

	public List<String> statisticsY(String currecyName, int year1, int year2){
		return displayStatisticsYears(currecyName,year1,year2);
	}

	private List<String> displayStatisticsYears(String currecyName, int year1, int year2) {
		List<String> res = new LinkedList<>();
		res.add("Statistics for " + currecyName);
		for (int i=year1 ; i<=year2 ; i++){
			res.add(displayOne(currecyName, "year ", i, currencyMongo.getByYear(i)));
		}
		return res;
	}
}
