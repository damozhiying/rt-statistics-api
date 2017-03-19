package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class StatisticsRepoService {

    private final StatisticsRepository repository;

    @Scheduled(fixedRate = AppConstants.MIN_IN_MILLI_SEC)
    void updateRepo() {
        int pastMinInSecs = LocalTime.now().minusMinutes(1).toSecondOfDay();
        repository.remove(pastMinInSecs);
    }
}
