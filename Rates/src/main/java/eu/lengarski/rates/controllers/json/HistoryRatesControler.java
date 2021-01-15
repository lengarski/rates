package eu.lengarski.rates.controllers.json;

import eu.lengarski.rates.models.dto.HistoryRequestDto;
import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.services.HistoryRateSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static eu.lengarski.rates.main.RatesConstants.JSON_API_PATH;

@RestController
@RequestMapping(JSON_API_PATH + "/history")
public class HistoryRatesControler {

    @Autowired
    private HistoryRateSercice historyRateSercice;

    @PostMapping
    public List<Rate> getHistoryRate(@RequestBody HistoryRequestDto historyRateRequest) {

        if (historyRateSercice.isRequestExists(historyRateRequest)) {
            throw new RuntimeException("Request id exists");
        } else {
            return historyRateSercice.getHistoryCoures(historyRateRequest);
        }
    }
}
