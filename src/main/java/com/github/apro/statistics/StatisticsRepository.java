package com.github.apro.statistics;

import com.github.apro.config.AppConstants;
import com.github.apro.transactions.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentMap;

@RequiredArgsConstructor
@Repository
public class StatisticsRepository {

    private final ConcurrentMap<Integer, Statistic> statsMap;

    public void add(@NotNull Transaction transaction) {
        final int timeStamp = LocalTime.ofSecondOfDay(transaction
                .getTimestamp() / AppConstants.MILLI_SECS_IN_SEC).toSecondOfDay();

        Statistic statistic = statsMap.getOrDefault(timeStamp, new Statistic());
        statistic.setSum(statistic.getSum() + transaction.getAmount());
        statistic.setCount(statistic.getCount() + 1);

        if (transaction.getAmount() > statistic.getMax()) {
            statistic.setMax(transaction.getAmount());
        }

        if (transaction.getAmount() < statistic.getMax()) {
            statistic.setMin(transaction.getAmount());
        }
    }

    void remove(int secondStamp) {
        statsMap.remove(secondStamp);
    }

    Statistic getMinuteStats() {
        Statistic stats = new Statistic();
        for (ConcurrentMap.Entry<Integer, Statistic> entry :
                statsMap.entrySet()) {

            stats.setCount(stats.getCount() + 1);
            stats.setSum(stats.getSum() + entry.getValue().getSum());

            stats.setMax(Math.max(stats.getMax(), entry.getValue().getMax()));
            stats.setMin(Math.min(stats.getMin(), entry.getValue().getMin()));
        }
        return stats;
    }
}
