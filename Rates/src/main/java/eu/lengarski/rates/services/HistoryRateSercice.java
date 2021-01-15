package eu.lengarski.rates.services;

import eu.lengarski.rates.models.dto.CurrentRequest;
import eu.lengarski.rates.models.dto.HistoryRequestDto;
import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.models.entity.RequestInformation;
import eu.lengarski.rates.repositories.RatesRepository;
import eu.lengarski.rates.repositories.RequestInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HistoryRateSercice {

    @Autowired
    private RequestInformationRepository requestRepository;

    @Autowired
    private RatesRepository ratesRepository;

    public HistoryRateSercice() {

    }


    public boolean isRequestExists(HistoryRequestDto request) {
        Optional<RequestInformation> byId = requestRepository.findById(request.getRequestId());
        return byId.isPresent();
    }

    public List<Rate> getHistoryCoures(HistoryRequestDto request) {
        RequestInformation info = new RequestInformation();

        info.setClient(request.getClient());
        info.setRequestId(request.getRequestId());
        info.setTimestamp(request.getTimestamp());

        requestRepository.save(info);

        Long startTime = request.getTimestamp();
        Long endTime = request.getTimestamp() + request.getPeriod() * 3600000;

        Iterable<Rate> all = ratesRepository.findByCurrancyAndTimestampBetween(request.getCurrency(), startTime, endTime);

        List<Rate> result = StreamSupport
                .stream(all.spliterator(), true)
                .collect(Collectors.toList());

        return result;
    }


}
