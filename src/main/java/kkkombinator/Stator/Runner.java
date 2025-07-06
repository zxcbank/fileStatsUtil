package kkkombinator.Stator;

import jdk.jshell.spi.ExecutionControl;
import kkkombinator.Configuration.Config;
import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.List;

import static kkkombinator.Stator.ArgManager.*;

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

    public void printStats() {
        printAllFields(floatStatistics);
        printAllFields(stringStatistic);
        printAllFields(integerStatistic);
    }

    private static void printAllFields(Object target) {
        if (target == null) return;

        Class<?> clazz = target.getClass();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {

                    field.setAccessible(true);

                    String name = field.getName();
                    String type = field.getType().getSimpleName();
                    Object value = field.get(target);

                    System.out.printf("  %s [%s] = %s\n", name, type, value);
                } catch (IllegalAccessException e) {
                    System.err.println("Error in access to filed " + field.getName() + ": " + e.getMessage());
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    public void Execute() {

        String filePath = config.path + config.prefix;
        for (var t : config.inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(t))) {
                String arg;
                while ((arg = reader.readLine()) != null) {
                    try {
                        Class<?> type = detectType(arg);
                        if (type.equals(String.class)) {
                            handleStringArg(arg, stringStatistic, filePath + config.stringsFileName);
                        } else if (type.equals(Integer.class)) {
                            handleIntegerArg(arg, integerStatistic, filePath + config.integersFileName);
                        } else if (type.equals(Float.class)) {
                            handleFloatArg(arg, floatStatistics, filePath + config.floatsFileName);
                        }
                    } catch (IllegalArgumentException e) {
//            System.err.println("no match for" + arg); for debug purposes
//            e.printStackTrace();
                    }
                }
                printStats();
            } catch (IOException e) {
                System.err.println("Error while reading file: " + e.getMessage());
            }
        }


    }
}
