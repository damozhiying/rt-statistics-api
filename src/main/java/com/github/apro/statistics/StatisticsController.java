package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import com.github.apro.transactions.Transaction;
import com.github.apro.transactions.TransactionController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.Callable;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticService service;

    @GetMapping(value = AppConstants.STATISTICS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Callable<Resource<Statistic>> getStats() {
        return () -> new Resource<>(service.getStats(),
                linkTo(methodOn(TransactionController.class).
                        createTransaction(new Transaction(0.0, 0L)
                                , BindingResultUtils
                                        .getBindingResult(Collections.emptyMap(), "")))
                        .withRel(AppConstants.REL_TRANSACTIONS));
    }
}
