package eu.lengarski.rates.services;

import eu.lengarski.rates.models.dto.CurrentRequest;
import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.models.entity.RequestInformation;
import eu.lengarski.rates.repositories.RatesRepository;
import eu.lengarski.rates.repositories.RequestInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CurrentRateService {

    @Autowired
    private RequestInformationRepository requestRepository;

    @Autowired
    private RatesRepository ratesRepository;

    public CurrentRateService() {

    }

    public boolean isRequestExists(CurrentRequest request) {
        Optional<RequestInformation> byId = requestRepository.findById(request.getRequestId());
        return byId.isPresent();
    }

    public List<Rate> getCurrentRates(CurrentRequest request) {
        RequestInformation info = new RequestInformation();

        info.setClient(request.getClient());
        info.setRequestId(request.getRequestId());
        info.setTimestamp(request.getTimestamp());

        new Date(request.getTimestamp());

        requestRepository.save(info);

        Long lastTimestamp = ratesRepository.findLastTimestamp();

        Iterable<Rate> all = ratesRepository.findByBaseAndTimestamp(request.getCurrency(), lastTimestamp);

        List<Rate> result = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        return result;
    }
}
