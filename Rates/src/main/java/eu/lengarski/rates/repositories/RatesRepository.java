package eu.lengarski.rates.repositories;

import eu.lengarski.rates.models.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RatesRepository extends CrudRepository<Rate, Long> {
}
