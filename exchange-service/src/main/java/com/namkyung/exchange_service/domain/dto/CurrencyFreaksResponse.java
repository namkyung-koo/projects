package com.namkyung.exchange_service.domain.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyFreaksResponse {

    private String base;
    private String date;
    private Map<String, String> rates;
}

/*
변환 예시(JSON)
{
  "date": "2024-04-19",
  "base": "KRW",
  "rates": {
    "USD": "0.000727",
    "JPY": "0.11045",
    "EUR": "0.000679",
    ...
  }
}
 */