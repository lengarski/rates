package eu.lengarski.rates.repositories;

import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.models.entity.RequestInformation;
import org.springframework.data.repository.CrudRepository;

public interface RequestInformationRepository extends CrudRepository<RequestInformation, String> {


}
