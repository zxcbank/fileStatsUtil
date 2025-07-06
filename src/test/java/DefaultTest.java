
import com.beust.jcommander.JCommander;
import kkkombinator.Configuration.Config;
import kkkombinator.Stator.Runner;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultTest {

    @Test
    public void defaultTest() {
        String[] argv = {"-s", "-p", "sample-",
                "/home/stas239/IdeaProjects/simpleFileStats/src/test/test_data/in1.txt",
                "/home/stas239/IdeaProjects/simpleFileStats/src/test/test_data/in2.txt"};
        Config config = new Config();
        JCommander.newBuilder()
                .addObject(config)
                .build()
                .parse(argv);

        Runner runner = new Runner(config);
        runner.Execute();
        assertEquals(3, runner.getFloatStatistics().numberOfFloats);
        assertEquals(3, runner.getIntegerStatistic().numberOfIntegers);
        assertEquals(6, runner.getStringStatistics().numberOfStrings);
    }
}
