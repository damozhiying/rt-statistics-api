package com.github.apro.statistics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Statistic {
    private double sum;
    private double max;
    private double min;
    private long count;

    public double getAvg() {
        if (count < 1) return 0;
        else return sum / count;
    }
}
