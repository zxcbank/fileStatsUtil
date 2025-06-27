package kkkombinator.Stator;

import jdk.jshell.spi.ExecutionControl;
import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import static java.lang.Math.max;
import static java.lang.Math.min;

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

    public static void handleStringArgs(String arg, StringStatistics stringStatistic)   {

        stringStatistic.maxStringLength = max(stringStatistic.maxStringLength, arg.length());
        stringStatistic.minStringLength = min(stringStatistic.minStringLength, arg.length());
        stringStatistic.numberOfStrings++;
    }

    public static void handleIntegerArg(String arg, IntegerStatistic integerStatistic)  {
        int number = Integer.parseInt(arg);

        integerStatistic.maxInteger = max(integerStatistic.maxInteger, number);
        integerStatistic.minInteger = min(integerStatistic.minInteger, number);
        integerStatistic.numberOfIntegers++;
        integerStatistic.sumOfIntegers += number;
    }

    public static void handleFloatArg(String arg, FloatStatistics floatStatistics) {
        float number = Float.parseFloat(arg);

        floatStatistics.maxFloats = max(floatStatistics.maxFloats, number);
        floatStatistics.minFloats = min(floatStatistics.minFloats, number);
        floatStatistics.numberOfFloats++;
        floatStatistics.sumOfFloats += number;
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
