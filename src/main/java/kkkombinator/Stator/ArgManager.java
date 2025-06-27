package kkkombinator.Stator;

import jdk.jshell.spi.ExecutionControl;
import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

public class ArgManager {
    public static Class<?> detectType(String value) throws IllegalArgumentException{
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        if (value.matches("-?\\d+")) {
            return Integer.class;
        }

        if (value.matches("-?\\d+\\.\\d+")) {
            return Float.class;
        }

        if (value.matches("\\p{L}+")) {
            return String.class;
        }

        throw new IllegalArgumentException("Wrong String: " + value);
    }

    public static void handleStringArgs(String arg, StringStatistics stringStatistic) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("no handleStringArgs");
    }

    public static void handleIntegerArg(String arg, IntegerStatistic integerStatistic) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("no handleIntegerArg");
    }

    public static void handleFloatArg(String Arg, FloatStatistics floatStatistics) throws ExecutionControl.NotImplementedException {
        throw new  ExecutionControl.NotImplementedException("no handleFloatArg");
    }

    public static void handleArg(String arg,
                                 StringStatistics stringStatistic,
                                 IntegerStatistic integerStatistic,
                                 FloatStatistics floatStatistics) {
        try {
            Class<?> type = detectType(arg);
            if (type.equals(String.class)) {
                handleStringArgs(arg, stringStatistic);
            } else if (type.equals(Integer.class)) {
                handleIntegerArg(arg, integerStatistic);
            } else if (type.equals(Float.class)) {
                handleFloatArg(arg, floatStatistics);
            }
        } catch (IllegalArgumentException e) {
//            System.err.println("no match for" + arg); for debug purposes
//            e.printStackTrace();
        }

    }
}
