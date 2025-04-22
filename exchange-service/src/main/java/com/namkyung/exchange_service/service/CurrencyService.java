package com.namkyung.exchange_service.service;

import com.namkyung.exchange_service.domain.dto.CurrencyFreaksResponse;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Value("${currencyfreaks.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> getExchangeRatesInKRWFormatted() {
        Map<String, String> rates = fetchRatesFromCurrencyFreaks();

        double krwRate = Double.parseDouble(rates.get("KRW"));
        double jpyRate = Double.parseDouble(rates.get("JPY"));
        double eurRate = Double.parseDouble(rates.get("EUR"));
        double gbpRate = Double.parseDouble(rates.get("GBP"));

        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        Map<String, String> result = new LinkedHashMap<>();

        result.put("USD", formatter.format(krwRate));
        result.put("JPY", formatter.format(krwRate / jpyRate * 100));
        result.put("EUR", formatter.format(krwRate / eurRate));
        result.put("GBP", formatter.format(krwRate / gbpRate));

        return result;
    }

    private Map<String, String> fetchRatesFromCurrencyFreaks() {
        String url = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apiKey;

        CurrencyFreaksResponse response = restTemplate.getForObject(url, CurrencyFreaksResponse.class);

        return response.getRates();
    }
}
