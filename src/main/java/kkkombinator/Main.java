package kkkombinator;


import com.beust.jcommander.JCommander;
import kkkombinator.Configuration.Config;
import kkkombinator.Stator.Runner;


public class Main {


    public static void main(String[] args) {
        Config config = new Config();
        JCommander.newBuilder()
                .addObject(config)
                .build()
                .parse(args);

        Runner runner = new Runner(config);
        runner.Execute();
    }
}