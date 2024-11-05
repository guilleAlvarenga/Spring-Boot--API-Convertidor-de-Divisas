package com.guilleS.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CurrencyConversionService {

    @Value("${currencylayer.api.key}")
    private String API_KEY;
    @Value("${currencylayer.base.url}")
    private String BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) throws Exception {
        String apiUrl = constructApiUrl(fromCurrency, toCurrency, amount);
        String response = restTemplate.getForObject(apiUrl, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        if (jsonResponse.has("error")) {
            throw new Exception("Error en la API: " + jsonResponse.getJSONObject("error").getString("info"));
        }

        return jsonResponse.getDouble("result");
    }

    private String constructApiUrl(String fromCurrency, String toCurrency, double amount) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("from", fromCurrency)
                .queryParam("to", toCurrency)
                .queryParam("amount", amount)
                .toUriString();
    }
}
