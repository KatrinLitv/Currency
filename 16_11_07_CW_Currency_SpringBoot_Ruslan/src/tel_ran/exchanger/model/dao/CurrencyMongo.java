package tel_ran.exchanger.model.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tel_ran.exchanger.model.dao.interfaces.ICurrency;
import tel_ran.exchanger.model.entity.Currency;
import tel_ran.exchanger.model.repositories.CurrencyRepository;

import java.time.LocalDate;
import java.util.*;


public class CurrencyMongo implements ICurrency<Currency,LocalDate> {
 //   private static String collectionName = "currency";

    @Autowired
    private CurrencyRepository repository;

//    @Autowired
//    MongoTemplate mongoTemplate; //


    public CurrencyMongo() {
    }

//    public CurrencyMongo(CurrencyRepository repository) {
//        this.repository = repository;
//    }

    static ObjectMapper mapper= new ObjectMapper();

//    public CurrencyMongo(CurrencyMongo currencyMongo) {
//    }

    private LocalDate convertStringToLocalDate(String string){
        LocalDate localDate = LocalDate.parse(string);
 //       System.out.println(string +localDate.toString());
        return localDate;
    }

    @Override
    public boolean addOne(Currency obj) {
//        System.out.println("repository "+repository);
        if ((obj==null) || (repository.exists(obj.getId()))){
            return false;
        }
//        System.out.println(obj.toString()+convertStringToLocalDate(obj.getId()));

        repository.save(obj);

        if (repository.exists(obj.getId())){
            return false;
        }
        return true;
    }


    @Override
    public Currency addOneFromJson(Map<String, Object> map) {
//        System.out.println("repository "+repository);
        Currency res = new Currency();
        try {
            String json = mapper.writeValueAsString(map);
//            System.out.println(json);
            res = mapper.readValue(json, Currency.class);
//            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        System.out.println("res = "+res);
        return addOne(res)?res:null;
    }


    @Override
    public Iterable<Currency> addFromJsons(Map<LocalDate, Map<String, Object>>  jsons) {
        List<Currency> currencyIterable = new LinkedList<>();
        Currency res;
        for (Map<String, Object> map : jsons.values()) {
            res = new Currency();
            try {
                String json = mapper.writeValueAsString(map);
                res = mapper.readValue(json, Currency.class);
                currencyIterable.add(res);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        repository.save(currencyIterable);
        return currencyIterable;
    }

    @Override
    public Iterable<Currency> add(Iterable<Currency> objs) {
        repository.save(objs);
        return objs;
    }

//
//    @Override
//    public boolean delete(Currency obj) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Currency> delete(Iterable<Currency> objs) {
//        return null;
//    }
//
//    @Override
//    public Currency getById(LocalDate s) {
//        return repository.findOne(s.toString());
//    }
//
//    @Override
//    public Currency getByIdMax() {
//        return null; //repository.findTopOrderByIdDesc();
//    }

    @Override
    public Iterable<Currency> getCurrencies() {
        return  repository.findAll();
    }

//    @Override
//    public Iterable<Currency> getByCurrency(String name, int YearFrom, int YearTo) {
//        return null;
//    }
//
//    @Override
//    public Double getAvgByCurrency(String name, int YearFrom, int YearTo) {
//        return null;
//    }
//
//    @Override
//    public Iterable<Currency> getByCurrencyAndYears(String name, int YearFrom, int YearTo) {
//        return null;
//    }
//
    @Override
    public void getStatisticByYears(String name, Integer yearFrom, Integer yearTo) {
        System.out.println("STATISTICS for CURRENCY  '" + name + "' ,YEARFROM = " + yearFrom+" YEARTO = " + yearTo);

        LocalDate dateStart = LocalDate.of(yearFrom,1,1);
        LocalDate dateEnd = LocalDate.of(yearTo,1,1);
        dateEnd=dateEnd.plusYears(1).minusDays(1);

        Iterable<Currency> currencies = repository.findByIds(dateStart.toString(),dateEnd.toString());
        float[] values = new float[yearTo-yearFrom+1];
        int[] counters = new int[yearTo-yearFrom+1];
        for (Currency currency : currencies) {
            LocalDate date = LocalDate.parse(currency.getId());
            values[date.getYear()-yearFrom]+=currency.getRates().get(name);
            counters[date.getYear()-yearFrom]+=1;
        }
        displayStatisticByMonth("Year",yearFrom,values,counters);

    }
//
//    @Override
//    public Iterable<Currency> getByCurrencyAndYearAndMonths(String name, int Year, int MonthFrom, int MonthTo) {
//        return null;
//    }
//
    @Override
    public void getStatisticByMonthsOfYear(String name, Integer year, Integer monthFrom, Integer monthTo) {
        System.out.println("STATISTICS for CURRENCY  '" + name + "' ,YEAR = " + year); //+" "+monthFrom+" "+monthTo

        LocalDate dateStart = LocalDate.of(year,monthFrom,1);
        LocalDate dateEnd = LocalDate.of(year,monthTo,1);
        dateEnd=dateEnd.plusMonths(1).minusDays(1);
//        System.out.println(dateEnd);

        Iterable<Currency> currencies = repository.findByIds(dateStart.toString(),dateEnd.toString());
        float[] values = new float[12];
        int[] counters = new int[12];
        for (Currency currency : currencies) {
            LocalDate date = LocalDate.parse(currency.getId());
            values[date.getMonthValue()-1]+=currency.getRates().get(name);
            counters[date.getMonthValue()-1]+=1;
        }
        displayStatisticByMonth("Month",1, values,counters);
    }

    private void displayStatisticByMonth(String text, int number, float[] values, int[] counters) {
        float sum = 0;
        for (int i = 0; i < values.length; i++) {
            values[i]=(values[i]/counters[i]);
            sum+=values[i];
        }

        for (int i = 0; i < values.length; i++) {
            printZ('*',500,text,number+i,values[i],sum);
        }

    }

    private void printZ(char c, int width, String text,  int number, float value, float sum) {
        int count = (int) (width*value/sum);
        StringBuilder reader = new StringBuilder("");
        while (count>0){
            reader=reader.append(c);
            count--;
        }
        System.out.println(text+" "+String.format("%04d",number)+" "+reader+" ("+value+" / "+sum+")");
    }

    private Set<String> getCurrenciesName(){
        Set<String> res = new HashSet<>();
        Currency currency = repository.findFirst1By();
        for (String key : currency.getRates().keySet()) {
            res.add(key);
            System.out.print(key+" ");
        }

        return res;
    }

//    @Override
//    public void createIndexes(Class clazz) {
////        DBCollection collection = mongoTemplate.getCollection(collectionName);
////        collection.dropIndexes();
////
////        Map<String,Object> indexes = getIndexes(clazz);
////        for (Map.Entry<String,Object> entry : indexes.entrySet()) {
////            collection.createIndex(entry.getKey());
////        }
//    }

//    private Map<String,Object> getIndexes(Class clazz){
//        Map<String,Object> fieldsAll = new HashMap<>();
//        if (clazz==null) return fieldsAll;
//
//        do {
//            Field[] fields = clazz.getDeclaredFields();
//            for(Field field:fields) {
//                if (field.isAnnotationPresent(Index.class)) {
//                    fieldsAll.put(field.getName(), field.getAnnotation(Index.class).unique());
//                }
//            }
//            clazz=clazz.getSuperclass();
//        } while(clazz!=Object.class);
//
//        return fieldsAll;
//    }




}
