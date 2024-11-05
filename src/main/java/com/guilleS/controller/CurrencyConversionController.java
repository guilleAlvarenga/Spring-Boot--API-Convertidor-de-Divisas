package com.guilleS.controller;

import com.guilleS.response.ConversionResponse;
import com.guilleS.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService currencyService;

    @GetMapping("/convert")
    public ConversionResponse convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        ConversionResponse response = new ConversionResponse();

        try {
            double result = currencyService.convertCurrency(from, to, amount);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String formattedResult = decimalFormat.format(result);
            response.setSuccess(true);
            response.setFrom(from);
            response.setTo(to);
            response.setAmount(amount);
            response.setResult(formattedResult);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setResult(e.getMessage());
        }

        return response;
    }

}
