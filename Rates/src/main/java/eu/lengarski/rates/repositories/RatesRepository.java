package eu.lengarski.rates.repositories;

import eu.lengarski.rates.models.entity.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RatesRepository extends CrudRepository<Rate, Long> {

    @Query(value = "select distinct r.timestamp from rate r order by r.timestamp desc limit 0, 1 ", nativeQuery = true)
    Long findLastTimestamp();

    Iterable<Rate> findByBaseAndTimestamp(String base,Long timestamp);

    Iterable<Rate> findByBase(String base);

    Iterable<Rate> findByCurrancyAndTimestampBetween(String currency, Long startTime, Long endTime);


}
