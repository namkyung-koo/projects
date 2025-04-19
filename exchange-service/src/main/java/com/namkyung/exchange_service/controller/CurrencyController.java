package com.namkyung.exchange_service.controller;

import com.namkyung.exchange_service.service.CurrencyService;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateController {

    private final CurrencyService currencyService;

    public ExchangeRateController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange-rate")
    public String showExchangeRates(Model model) {
        Map<String, String> exchangeRates = currencyService.getExchangeRatesInKRWFormatted();
        model.addAttribute("exchangeRates", exchangeRates);
        return "exchange-rate";
    }
}
