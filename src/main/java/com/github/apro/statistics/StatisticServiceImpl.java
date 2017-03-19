package com.github.apro.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticsRepository repository;

    @Override
    public Statistic getStats() {
        return repository.getMinuteStats();
    }
}
