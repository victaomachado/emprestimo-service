package com.victortech.emprestimo_service.controller;

import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer-loans")
public class LoansController {

    @PostMapping
    public Map<String, Object> getAvaliableLoans(@RequestBody Map<String, Object> request) {

        int age = (int) request.get("age");
        double income = (double) request.get("income");
        String location = (String) request.get("location");
        String name = (String) request.get("name");

        List<Map<String, Object>> loans = new ArrayList<Map<String, Object>>();

        if (income <= 3000) {
            loans.add(createLoans("PERSONAL", 4));
            loans.add(createLoans("GUARANTEED", 3));
        }

        if (income <= 5000) {
            loans.add(createLoans("CONSIGNAMENT", 2));
        }

        if (income > 3000 && income <= 5000 && "SP".equalsIgnoreCase(location)){
            loans.add(createLoans("PERSONAL", 4));
            loans.add(createLoans("GUARANTEED", 3));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("customer", name);
        response.put("loans", loans);

        return response;
    }

    private Map<String, Object> createLoans(String type, int interestRate) {
        Map<String, Object> loan = new HashMap<>();
        loan.put("type", type);
        loan.put("interest_rate", interestRate);
        return loan;
    }
}
