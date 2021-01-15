package eu.lengarski.rates.schedules;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lengarski.rates.models.entity.Rate;
import eu.lengarski.rates.models.json.Responce;
import eu.lengarski.rates.repositories.RatesRepository;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eu.lengarski.rates.main.RatesConstants.FIXER_IO_LATEST_URL;
import static eu.lengarski.rates.main.RatesConstants.FIXER_KEY;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private RatesRepository ratesRepository;


    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(FIXER_IO_LATEST_URL + "?access_key=" + FIXER_KEY + "&base=EUR").newBuilder();

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    System.out.println(json);

                    ObjectMapper mapper = new ObjectMapper();

                    Responce responce = mapper.readValue(json, Responce.class);

                    List<String> currancies = new ArrayList<String>();

                    List<Rate> allRates = new ArrayList<Rate>();

                    Date date = new Date();

                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(responce.getDate());
                    } catch (ParseException e) {
                        logger.error(e.getMessage());
                    }

                    // effective final
                    final Date finalDate = date;
                    responce.getRates().forEach((currancy, amount) -> {
                        Rate rate = new Rate();
                        rate.setAmount(amount);
                        rate.setBase(responce.getBase());
                        rate.setCurrancy(currancy);
                        rate.setDate(finalDate);
                        rate.setTimestamp(responce.getTimestamp());

                        allRates.add(rate);


                        currancies.add(currancy);

                    });

                    ratesRepository.saveAll(allRates);

       //             downloadWithDiferentBase(currancies);

                } else {
                    logger.error("Responce fail ? " + response.isSuccessful());
                }
            }
        });
        logger.info("Job execute date {}", dateFormat.format(new Date()));
    }

    private void downloadWithDiferentBase(List<String> currancies) {

        for (String currancy : currancies) {
            //TODO: Refector this !
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    OkHttpClient client = new OkHttpClient();
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(FIXER_IO_LATEST_URL + "?access_key=" + FIXER_KEY + "&base=" + currancy).newBuilder();

                    String url = urlBuilder.build().toString();
                    Request request = new Request.Builder().url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            logger.error(e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json = response.body().string();

                                ObjectMapper mapper = new ObjectMapper();

                                Responce responce = mapper.readValue(json, Responce.class);

                                List<Rate> allRates = new ArrayList<Rate>();

                                Date date = new Date();

                                try {
                                    date = new SimpleDateFormat("yyyy-MM-dd").parse(responce.getDate());
                                } catch (ParseException e) {
                                    logger.error(e.getMessage());
                                }

                                // effective final
                                final Date finalDate = date;
                                responce.getRates().forEach((currancy, amount) -> {
                                    Rate rate = new Rate();
                                    rate.setAmount(amount);
                                    rate.setBase(responce.getBase());
                                    rate.setCurrancy(currancy);
                                    rate.setDate(finalDate);
                                    rate.setTimestamp(responce.getTimestamp());
                                    allRates.add(rate);
                                });
                                ratesRepository.saveAll(allRates);
                            } else {
                                logger.error("Responce fail ? " + response.isSuccessful());
                            }
                        }
                    });
                }
            });
            t.start();
        }
    }
}