package kkkombinator;


import com.beust.jcommander.JCommander;
import kkkombinator.Configuration.Config;
import kkkombinator.Stator.Runner;

import java.lang.reflect.Field;

public class Main {


    public static void printAllFields(Object target) {
        if (target == null) return;

        Class<?> clazz = target.getClass();
        System.out.println("Fields of " + clazz.getSimpleName() + ":");

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

    public static void main(String[] args) {

        String[] argv = {"-s", "-a", "-p", "sample-", "test_data/file1.txt", "test_data/file2.txt"};
        Config config = new Config();
        JCommander.newBuilder()
                .addObject(config)
                .build()
                .parse(argv);

        Runner runner = new Runner(config);
        runner.Execute();
    }
}