package kkkombinator.Stator;

import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static kkkombinator.Stator.OutputWriter.fileWriteArg;

public class ArgManager {
    public static Class<?> detectType(String value) throws IllegalArgumentException {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        if (value.matches("-?\\d+")) {
            return Integer.class;
        }

        if (value.matches("-?\\d+\\.\\d+") ||  value.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
            return Float.class;
        }

        return String.class;
    }

    public static void manageStringStat(String arg, StringStatistics stringStatistic) {

        stringStatistic.maxStringLength = max(stringStatistic.maxStringLength, arg.length());
        stringStatistic.minStringLength = min(stringStatistic.minStringLength, arg.length());
        stringStatistic.numberOfStrings++;
    }

    public static void manageIntegerStat(String arg, IntegerStatistic integerStatistic) {
        long number = Long.parseLong(arg);

        integerStatistic.maxInteger = max(integerStatistic.maxInteger, number);
        integerStatistic.minInteger = min(integerStatistic.minInteger, number);
        integerStatistic.numberOfIntegers++;
        integerStatistic.sumOfIntegers += number;
    }

    public static void manageFloatStat(String arg, FloatStatistics floatStatistics) {
        float number = Float.parseFloat(arg);

        floatStatistics.maxFloats = max(floatStatistics.maxFloats, number);
        floatStatistics.minFloats = min(floatStatistics.minFloats, number);
        floatStatistics.numberOfFloats++;
        floatStatistics.sumOfFloats += number;
    }


    public static void handleFloatArg(String arg, FloatStatistics floatStatistics, String filePath, boolean flush) {
        manageFloatStat(arg, floatStatistics);
        fileWriteArg(filePath, arg, flush);
    }

    public static void handleStringArg(String arg, StringStatistics stringStatistic, String filePath, boolean flush) {
        manageStringStat(arg, stringStatistic);
        fileWriteArg(filePath, arg, flush);
    }

    public static void handleIntegerArg(String arg, IntegerStatistic integerStatistic, String filePath, boolean flush) {
        manageIntegerStat(arg, integerStatistic);
        fileWriteArg(filePath, arg, flush);
    }
}
