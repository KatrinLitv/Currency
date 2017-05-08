package tel_ran.currency.mongoDB.repo;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import tel_ran.currency.mongoDB.entity.CurrencyMap;


public interface CurrencyRepository extends CrudRepository<CurrencyMap, Date>{
	List<CurrencyMap> findByYearAndMonth(int year, int month);
	List<CurrencyMap> findByYear(int year);
}
