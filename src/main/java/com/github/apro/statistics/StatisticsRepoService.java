package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class StatisticsRepoService {

    private final StatisticsRepository repository;

    @Scheduled(fixedRate = AppConstants.MIN_IN_MILLI_SEC)
    void updateRepo() {
        long lastEpochMinInSecs = Instant.now().getEpochSecond() - AppConstants.SECS_IN_MIN;
        repository.remove(lastEpochMinInSecs);
    }
}
