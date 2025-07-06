package kkkombinator.Stator;

import kkkombinator.Statistics.FloatStatistics;
import kkkombinator.Statistics.IntegerStatistic;
import kkkombinator.Statistics.StringStatistics;

import java.lang.reflect.Field;

public class StatsPrinter {
    public static void printAllFields(Object target) {
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

    public static void printSmallStats(FloatStatistics floatStatistics, StringStatistics stringStatistics, IntegerStatistic integerStatistic) {
        System.out.println("Number of Floats " + floatStatistics.numberOfFloats);
        System.out.println("Number of Strings " + stringStatistics.numberOfStrings);
        System.out.println("Number of Integers " + integerStatistic.numberOfIntegers);
    }

    public static void printStats(FloatStatistics floatStatistics, StringStatistics stringStatistics, IntegerStatistic integerStatistics) {
        printAllFields(floatStatistics);
        printAllFields(stringStatistics);
        printAllFields(integerStatistics);
    }
}
