package kkkombinator.Configuration;

import com.beust.jcommander.converters.IParameterSplitter;

import java.util.Arrays;
import java.util.List;

public class SpaceSplitter implements IParameterSplitter {
    public List<String> split(String value) {
        return Arrays.asList(value.split(" "));
    }
}
