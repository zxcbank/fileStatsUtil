package kkkombinator;


import com.beust.jcommander.JCommander;
import kkkombinator.Configuration.Config;
import kkkombinator.Stator.Runner;

import java.lang.reflect.Field;

public class Main {




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