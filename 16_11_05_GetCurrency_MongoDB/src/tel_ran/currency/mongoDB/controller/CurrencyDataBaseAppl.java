package tel_ran.currency.mongoDB.controller;

import java.time.LocalDate;

import tel_ran.currency.mongoDB.dao.CurrencyTemplate;

public class CurrencyDataBaseAppl {

	private static final int N_YEARS = 10;

	public static void main(String[] args) {
		CurrencyTemplate currencyMongo = new CurrencyTemplate
				("mongodb://root:Paris2005@ds053126.mlab.com:53126/","katrin_litv","CurrencyFixer");
		int curYear = LocalDate.now().getYear();
		for (int i=0; i<N_YEARS ; i++){
			int year=curYear-1-i;
			LocalDate date1 = LocalDate.of(year,1,1);
			LocalDate date2 = LocalDate.of(year,12,31);
			//System.out.println((""+date1 +"-"+date2));
			currencyMongo.saveMany(date1, date2);
		}
		LocalDate date3 = LocalDate.of(curYear,1,1);
		LocalDate date4 = LocalDate.now();
		currencyMongo.saveMany(date3, date4);
	}

}
