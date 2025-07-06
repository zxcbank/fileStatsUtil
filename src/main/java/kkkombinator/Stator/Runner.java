package kkkombinator.Stator;

import kkkombinator.Configuration.Config;
import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static kkkombinator.Stator.ArgManager.*;
import static kkkombinator.Stator.StatsPrinter.printSmallStats;
import static kkkombinator.Stator.StatsPrinter.printStats;

public class Runner {
    FloatStatistics floatStatistics;
    IntegerStatistic integerStatistic;
    StringStatistics stringStatistic;

    Config config;

    public Runner(Config config) {
        this.floatStatistics = new FloatStatistics();
        this.integerStatistic = new IntegerStatistic();
        this.stringStatistic = new StringStatistics();
        this.config = config;
    }


    public void Execute() {

        String filePath = config.path + config.prefix;
        boolean flushFloat = !config.additional;
        boolean flushInteger = !config.additional;
        boolean flushString = !config.additional;

        for (var t : config.inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(t))) {
                String arg;
                while ((arg = reader.readLine()) != null) {
                    try {
                        Class<?> type = detectType(arg);
                        if (type.equals(String.class)) {
                            handleStringArg(arg, stringStatistic, filePath + config.stringsFileName, flushString);
                            flushString = false;
                        } else if (type.equals(Integer.class)) {
                            handleIntegerArg(arg, integerStatistic, filePath + config.integersFileName, flushInteger);
                            flushInteger = false;
                        } else if (type.equals(Float.class)) {
                            handleFloatArg(arg, floatStatistics, filePath + config.floatsFileName, flushFloat);
                            flushFloat = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("no match for" + arg);
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                System.err.println("Error while reading file: " + e.getMessage());
            }
        }
        if (config.fullStatistics) {
            printStats(floatStatistics, stringStatistic, integerStatistic);
        } else {
            printSmallStats(floatStatistics, stringStatistic, integerStatistic);
        }

    }
}
