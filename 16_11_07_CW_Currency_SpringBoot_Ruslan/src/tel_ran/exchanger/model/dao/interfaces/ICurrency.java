package tel_ran.exchanger.model.dao.interfaces;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Ruslan on 22/10/16.
 */
public interface ICurrency<T,ID> {
    //Adding
    boolean addOne(T obj);
    Iterable<T> add(Iterable<T> objs);

     T addOneFromJson(Map<String, Object> json);
    Iterable<T> addFromJsons(Map<LocalDate, Map<String, Object>>  jsons);

//    //Deleting
//    boolean delete(T obj);
//    Iterable<T> delete(Iterable<T> objs);
//
//    //Getting
//
//    T getById(ID id);
//    T getByIdMax();

    Iterable<T> getCurrencies();

//    Iterable<T> getByCurrency(String name, int YearFrom, int YearTo);
//    Double getAvgByCurrency(String name, int YearFrom, int YearTo);
//
//    Iterable<T> getByCurrencyAndYears(String name, int YearFrom, int YearTo);
    void getStatisticByYears(String name, Integer yearFrom, Integer yearTo);
//
//    Iterable<T> getByCurrencyAndYearAndMonths(String name, int Year, int MonthFrom, int MonthTo);
    void getStatisticByMonthsOfYear(String name, Integer Year, Integer MonthFrom, Integer MonthTo);
//
//    //Indexes
//    void createIndexes(Class clazz);

}
