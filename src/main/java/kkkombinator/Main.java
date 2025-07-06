package kkkombinator;


import com.beust.jcommander.JCommander;
import kkkombinator.Configuration.Config;
import kkkombinator.Stator.Runner;


public class Main {


    public static void main(String[] args) {

        String[] argv = {"-s", "-p", "sample-", "test_data/in1.txt", "test_data/in2.txt"};
        Config config = new Config();
        JCommander.newBuilder()
                .addObject(config)
                .build()
                .parse(argv);

        Runner runner = new Runner(config);
        runner.Execute();
    }
}