package com.simurg.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final IFraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "{customerId}")
    public Boolean isFraudster(@PathVariable("customerId") Long customerId) {
        log.info("isFraudster {}", customerId);
        return fraudCheckHistoryService.isFraudulentCustomer(customerId);
    }

}
