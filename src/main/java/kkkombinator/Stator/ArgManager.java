package kkkombinator.Stator;

import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

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

    public static void manageStringStat(String arg, StringStatistics stringStatistic)   {

        stringStatistic.maxStringLength = max(stringStatistic.maxStringLength, arg.length());
        stringStatistic.minStringLength = min(stringStatistic.minStringLength, arg.length());
        stringStatistic.numberOfStrings++;
    }

    public static void manageIntegerStat(String arg, IntegerStatistic integerStatistic)  {
        int number = Integer.parseInt(arg);

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

    public static <T> void fileWriteArg(String filePath, T arg) {
        var path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Path createdFile = Files.createFile(path);
                Files.write(createdFile, arg.toString().getBytes());
            } catch (IOException e) {
                System.err.println("не удалось создать файл или записать в файл по пути: " + filePath);
            }
        } else {
            try {
                Files.write(path, arg.toString().getBytes());
            } catch( IOException e) {
                System.err.println("не удалось записать информацию в файл по пути: " + filePath);
            }
        }
    }

    public static void handleFloatArg(String arg, FloatStatistics floatStatistics, String filePath) {
        manageFloatStat(arg, floatStatistics);
        fileWriteArg(filePath, arg);
    }

    public static void handleStringArg(String arg,  StringStatistics stringStatistic, String filePath) {
        manageStringStat(arg, stringStatistic);
        fileWriteArg(filePath, arg);
    }

    public static void handleIntegerArg(String arg,  IntegerStatistic integerStatistic, String filePath) {
        manageIntegerStat(arg, integerStatistic);
        fileWriteArg(filePath, arg);
    }
}
