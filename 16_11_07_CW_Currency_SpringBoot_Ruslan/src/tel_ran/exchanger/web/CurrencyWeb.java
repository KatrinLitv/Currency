package tel_ran.exchanger.web;

import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruslan on 03/11/16.
 */
public class CurrencyWeb {
    RestTemplate restTemplate= new RestTemplate();
    String url= "http://api.fixer.io/";
    String date;
    Map<String,Double> currencies = new HashMap<>();

    public CurrencyWeb() {
            url=url+"latest";
            this.date=(new Date()).toString();
    }
    public CurrencyWeb(String date) {
            this.date=date;
            url=url+date;
    }

    public Map<String,Object> getJson(){
        return restTemplate.getForObject(url,Map.class);
    }

    public double getCurrencyValue(String s) {
        Map<String,Object> json = getJson();
        Map<String,Double> sets = (Map<String, Double>) json.get("rates");
        return sets.get(s);
    }

    public double getCurrencyValue(String s, Map<String, Object> json) {
        Map<String,Double> sets = (Map<String, Double>) json.get("rates");
        return sets.get(s);
    }


    private void get_Cunrrency() {
        Map<String,Object> json = getJson();
        Map<String,Double> sets = (Map<String, Double>) json.get("rates");
        StringWriter writer= new StringWriter();
        for (String s : sets.keySet()) {
            writer.append(" ").append(s);
        }
        displayMessage(writer.toString());
    }

    private void get_Cunrrencies(String[] operations) {
        for (String operation : operations) {
            System.out.println(operation);
        }

    }
    private void get_Convert(String[] operations) {
//        Map<String, Object> currency = getC;
    }


    public static void displayMessage(String str){
        System.out.println(str);
    }



//
//    currencies список валюты
//
//    currency ILS
//    колич шеккелей в евро
//    дай курс рубля
//
//    convert ILS RUB 100
//

//    static String url= "http://api.fixer.io/latest";

    //{"base":"EUR","date":"2016-11-02","rates":{"AUD":1.4479,"BGN":1.9558,"BRL":3.5967,"CAD":1.484,"CHF":1.0787,"CNY":7.4983,"CZK":27.022,
    // "DKK":7.4395,"GBP":0.90063,"HKD":8.604,"HRK":7.513,"HUF":308.06,"IDR":14480.74,"ILS":4.2334,"INR":73.971,"JPY":114.62,"KRW":1268.46,
    // "MXN":21.495,"MYR":4.6366,"NOK":9.0735,"NZD":1.5232,"PHP":53.639,"PLN":4.3255,"RON":4.5028,"RUB":70.3983,"SEK":9.891,"SGD":1.536,
    // "THB":38.783,"TRY":3.458,"USD":1.1095,"ZAR":14.8423}}


}
