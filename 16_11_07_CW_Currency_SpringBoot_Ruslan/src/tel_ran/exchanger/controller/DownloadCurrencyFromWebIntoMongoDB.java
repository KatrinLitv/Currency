package tel_ran.exchanger.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.client.RestTemplate;
import tel_ran.exchanger.model.dao.CurrencyMongo;
import tel_ran.exchanger.model.dao.interfaces.ICurrency;
import tel_ran.exchanger.model.repositories.CurrencyRepository;
import tel_ran.exchanger.web.CurrencyWeb;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Ruslan on 03/11/16.
 * {base=EUR, date=1999-01-04, rates={AUD=1.91, CAD=1.8004, CHF=1.6168, CYP=0.58231, CZK=35.107, DKK=7.4501, EEK=15.6466, GBP=0.7111, HKD=9.1332, HUF=251.48, ISK=81.48, JPY=133.73, KRW=1398.59, LTL=4.717, LVL=0.6668, MTL=0.4432, NOK=8.855, NZD=2.2229, PLN=4.0712, ROL=13111.0, SEK=9.4696, SGD=1.9554, SIT=189.045, SKK=42.991, TRL=372274.0, USD=1.1789, ZAR=6.9358, EUR=1.0}, id=1999-01-04}
 */

public class DownloadCurrencyFromWebIntoMongoDB {
    private static RestTemplate restTemplate= new RestTemplate();
//    static String url= "http://api.fixer.io/latest";
    private static CurrencyRepository currencyRepository;
    private static CurrencyMongo currencyMongo;

    public static void main(String[] args) {
        try(AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml")) {
            currencyRepository = ctx.getBean(CurrencyRepository.class);
            currencyMongo = ctx.getBean(CurrencyMongo.class);
            Map<LocalDate, Map<String, Object>> jsons = getCurrencyFromWeb();
            if (jsons != null) sendCurrencyToMongo(jsons);
        }

        //MapFromWeb count =6516
    }

    private static Map<LocalDate,Map<String,Object>> getCurrencyFromWeb() {
        Map<LocalDate, Map<String, Object>> res = new LinkedHashMap<>();
        LocalDate date = LocalDate.now();
        //date = LocalDate.of(2000,01,04);
        Map<String,Object> json;
        while(true){
            try {
                json= new CurrencyWeb(date.toString()).getJson();
                res.put(date,json);
                //System.out.println(json);
            }
            catch (Exception e){
                break;
            }
            date=date.minusDays(1);
        }
        System.out.println("MapFromWeb count ="+res.size());
        return res;
    }


    private static void sendCurrencyToMongo(Map<LocalDate, Map<String, Object>> jsons) {
//        ICurrency mongo = new CurrencyMongo(currencyRepository);
        ICurrency mongo = currencyMongo;
        Map<String, Object> json;
        for (Map.Entry<LocalDate, Map<String, Object>> entry : jsons.entrySet()) {
            json=entry.getValue();

            json.remove("base");
            json.put("id",entry.getKey().toString());
            ((Map<String,Double>)json.get("rates")).put("EUR",1d);
        }
        mongo.addFromJsons(jsons);
    }

//    private static void sendCurrencyToMongo(Map<LocalDate, Map<String, Object>> jsons) {
//        ICurrency mongo = new CurrencyMongo(currencyRepository);
//        Map<String, Object> json;
//        for (Map.Entry<LocalDate, Map<String, Object>> entry : jsons.entrySet()) {
//            json=entry.getValue();
//
//            json.remove("base");
//            json.put("id",entry.getKey().toString());
//            ((Map<String,Double>)json.get("rates")).put("EUR",1d);
//
////            System.out.println(json);
////            {base=EUR, date=1999-01-04, rates={AUD=1.91, CAD=1.8004, CHF=1.6168, CYP=0.58231, CZK=35.107, DKK=7.4501, EEK=15.6466, GBP=0.7111, HKD=9.1332, HUF=251.48, ISK=81.48, JPY=133.73, KRW=1398.59, LTL=4.717, LVL=0.6668, MTL=0.4432, NOK=8.855, NZD=2.2229, PLN=4.0712, ROL=13111.0, SEK=9.4696, SGD=1.9554, SIT=189.045, SKK=42.991, TRL=372274.0, USD=1.1789, ZAR=6.9358, EUR=1.0}, id=1999-01-04}
//            mongo.addOneFromJson(json);
//        }
//    }

}

