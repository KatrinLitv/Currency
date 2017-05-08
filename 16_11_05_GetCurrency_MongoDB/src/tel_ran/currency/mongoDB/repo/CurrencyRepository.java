package tel_ran.currency.mongoDB.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tel_ran.currency.mongoDB.entity.CurrencyMap;


public interface CurrencyRepository extends CrudRepository<CurrencyMap, LocalDate>{
	List<CurrencyMap> findByYearAndMonth(int year, int month);
	List<CurrencyMap> findByYear(int year);
}
