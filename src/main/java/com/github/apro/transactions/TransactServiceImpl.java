package com.github.apro.transactions;

import com.github.apro.config.AppConstants;
import com.github.apro.statistics.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransactServiceImpl implements TransactService {

    private final StatisticsRepository repository;

    @Override
    public void save(@NotNull Transaction transaction) {
        if ((Instant.now().toEpochMilli() -
                transaction.getTimestamp()) / AppConstants.MILLI_SECS_IN_SEC
                <= AppConstants.SECS_IN_MIN) {
            repository.add(transaction);
        }
    }
}
