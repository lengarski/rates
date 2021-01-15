package eu.lengarski.rates.controllers.json;

import eu.lengarski.rates.models.dto.CurrentRequest;
import eu.lengarski.rates.models.dto.RequestDto;
import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.services.CurrentRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static eu.lengarski.rates.main.RatesConstants.JSON_API_PATH;

@RestController
@RequestMapping(JSON_API_PATH + "/current")
public class CurrentRatesController {

    @Autowired
    private CurrentRateService currentRateService;

    @PostMapping
    public List<Rate> getCurrentRates(@RequestBody CurrentRequest request) {
        if (currentRateService.isRequestExists(request)) {
            throw new RuntimeException("Request exists ");
        }
        else{
            return currentRateService.getCurrentRates(request);
        }
    }
}


