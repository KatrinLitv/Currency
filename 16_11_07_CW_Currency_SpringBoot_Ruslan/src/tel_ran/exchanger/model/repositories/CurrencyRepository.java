package tel_ran.exchanger.model.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tel_ran.exchanger.model.entity.Currency;

public interface CurrencyRepository  extends CrudRepository<Currency, String> {

//    Currency findById(String id);

    Iterable<Currency> findAll();
    Currency findFirst1By();

    @Query("{'id':{'$gte':?0,'$lte':?1}}")
    Iterable<Currency> findByIds(String dateStart, String dateEnd); //any name

    @Query("{'date':{'$gte':?0,'$lte':?1}}")
    Iterable<Currency> findByDates(String dateStart, String dateEnd); //any name

//    @Query("select a from Currency a where a.id = ? ")
//    Iterable<Currency> findAvg(String date);
//    @Query("{'salary':{'$lt':?0}}")
//    Iterable<Person> findBySalaryLessThenOrderBySalaryDesc(int salary); //any name

//    @Query("select a from Asset a where ownerId = ? and timeUpdated > ?")
//    public List<Currency> findByOwnerIdAndGreaterThan(final String ownerId, Date timeUpdated);
//
//    Currency findTopOrderByIdDesc();
//    findFirst5ByOrderByPublicationDateDesc()
//    findByOwnerIdAndGreaterThan
//            findByOwnerIdAndTimeUpdatedGreaterThan
//
//    findAllByOrderByFooAsc(...) findEveryOrderByFooAsc(...)
//List<Tod> findByTitleOrderByTitleAscDescriptionDesc(String title);
//    List<Tod> findByDescriptionContainsOrTitleContainsAllIgnoreCaseOrderByTitleAsc(String descriptionPart,
//                                                                                    String titlePart);
//
///    List<Tod> findByTitleOrderByTitleAsc(String title);
//
//    Iterable<Currency> findByNameAndPassword(String name, String password);
//
//
//    Role findTopByIdOrderByIdDesc();
//
//    Iterable<Person> findByAdressCity(String city);
//    Iterable<Person> findByBirthYearBetweenOrderByBirthYearAsc(int yearFrom, int yearTo);
//
//    @Query("{'salary':{'$lt':?0}}")
//    Iterable<Person> findBySalaryLessThenOrderBySalaryDesc(int salary); //any name
//
//
//    SELECT t FROM Too t WHERE
//    LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR
//    LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))
//    ORDER BY t.title ASC
//


//    default boolean getByNameAndPassword(String name,String password){
//        return false;
//    }
//    Role findByIdOrderByIdDesc();
//    Role findTopByIdOrderByIdDesc();

//    Iterable<Person> findByAdressCity(String city);
//    Iterable<Person> findByBirthYearBetweenOrderByBirthYearAsc(int yearFrom, int yearTo);
//
//    @Query("{'salary':{'$lt':?0}}")
//    Iterable<Person> findBySalaryLessThenOrderBySalaryDesc(int salary); //any name


}

