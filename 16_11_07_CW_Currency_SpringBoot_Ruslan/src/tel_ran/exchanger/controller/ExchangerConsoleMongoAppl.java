package tel_ran.exchanger.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import tel_ran.exchanger.model.dao.CurrencyMongo;
import tel_ran.exchanger.model.repositories.CurrencyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * Created by Ruslan on 03/11/16.
 * {base=EUR, date=1999-01-04, rates={AUD=1.91, CAD=1.8004, CHF=1.6168, CYP=0.58231, CZK=35.107, DKK=7.4501, EEK=15.6466, GBP=0.7111, HKD=9.1332, HUF=251.48, ISK=81.48, JPY=133.73, KRW=1398.59, LTL=4.717, LVL=0.6668, MTL=0.4432, NOK=8.855, NZD=2.2229, PLN=4.0712, ROL=13111.0, SEK=9.4696, SGD=1.9554, SIT=189.045, SKK=42.991, TRL=372274.0, USD=1.1789, ZAR=6.9358, EUR=1.0}, id=1999-01-04}
 */
public class ExchangerConsoleMongoAppl {
    private static final java.lang.String SPLITTER = " ";
    private static CurrencyRepository currencyRepository;
    private static CurrencyMongo currencyMongo;

    public static void main(String[] args) {
        try(AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml")) {
            currencyRepository = ctx.getBean(CurrencyRepository.class);
            currencyMongo = ctx.getBean(CurrencyMongo.class);
            consoleAppl();
        }
    }

    public static void consoleAppl(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line=null;
//        CurrencyMongo currencyMongo = new CurrencyMongo();
        Boolean exit = false;
        while (true && !exit){
            displayMessage("Input operation: 1(getStatisticByMonthsOfYear) 2(getStatisticByYears)  3 list of curries  4 - exit");
            try {
                line= reader.readLine();
            } catch (IOException e) {
                displayMessage(e.getMessage());
            }
            if (line==null) {
                displayMessage("Input operation: 1(getStatisticByMonthsOfYear) 2(getStatisticByYears)  3 list of curries  4 - exit");
                continue;
            }

            if (line.contains("exit")) break;
            String[] operations = line.split(SPLITTER);
            if (operations.length==0 ) continue;

            switch (operations[0]) {
                case "1":
                    try {
                        if (operations.length<=2) continue;

                        String currencyName = operations[1];
                        Integer year = 0;
                        try {
                            year = Integer.parseInt(operations[2]);
                        } catch (Exception e) {
                            displayMessage(e.getMessage());
                            continue;
                        }
                        Object[] values = {currencyName,year,1,12};
                        Method method = currencyMongo.getClass().getDeclaredMethod("getStatisticByMonthsOfYear"
                                , new Class[]{String.class, Integer.class, Integer.class, Integer.class});
                        method.setAccessible(true);
                        method.invoke(currencyMongo,values);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        if (operations.length<=3) continue;

                        String currencyName = operations[1];
                        Integer year1 = 0;
                        Integer year2 = 0;
                        try {
                            year1 = Integer.parseInt(operations[2]);
                            year2 = Integer.parseInt(operations[3]);
                        } catch (Exception e) {
                            displayMessage(e.getMessage());
                            continue;
                        }
                        Object[] values = {currencyName,year1,year2};
                        Method method = currencyMongo.getClass().getDeclaredMethod("getStatisticByYears"
                                , new Class[]{String.class, Integer.class, Integer.class});
                        method.setAccessible(true);
                        method.invoke(currencyMongo,values);
                    } catch (Exception e) {
                        displayMessage(e.getMessage());
                    }
                    break;
                case "3":
                    Method method = null;
                    try {
                        method = currencyMongo.getClass().getDeclaredMethod("getCurrenciesName");
                        method.setAccessible(true);
                        method.invoke(currencyMongo);
                    } catch (Exception e) {
                        displayMessage(e.getMessage());
                    }
                    break;
                case "4":
                    exit=true;
                    break;
            }

        }
    }
    public static void displayMessage(String str){
        System.out.println(str);
    }


}

