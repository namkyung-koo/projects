package com.namkyung.exchange_service.controller;

import com.namkyung.exchange_service.service.CurrencyService;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange-rate")
    public String showExchangeRates(Model model) {
        Map<String, String> exchangeRates = currencyService.getExchangeRatesInKRWFormatted();

        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime nowKst = nowUtc.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        model.addAttribute("exchangeRates", exchangeRates);
        model.addAttribute("utcTime", nowUtc.format(formatter));
        model.addAttribute("kstTime", nowKst.format(formatter));

        return "exchange-rate";
    }
}
