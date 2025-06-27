package kkkombinator.Stator;

import jdk.jshell.spi.ExecutionControl;
import kkkombinator.Configuration.Config;
import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

    public void printStats() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("no printingOfStats");
    }

    public void Execute() {
        for (var t : config.inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(t))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }


}
