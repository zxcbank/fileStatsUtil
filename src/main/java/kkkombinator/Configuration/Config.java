package kkkombinator.Configuration;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
    @Parameter(names = "-o")
    public String path = "";

    @Parameter(names = "-p", description = "prefix in file names")
    public String prefix = "";

    @Parameter(listConverter = FileListConverter.class, splitter = SpaceSplitter.class, description = "<file1> <file2> <file3>... ")
    public List<File> inputFiles = new ArrayList<>();

    @Parameter(names = "-a", description = "constraint in overwriting output files.")
    public Boolean additional=false;

    @Parameter(names = "-s", description = "constraint for small statistic.")
    public Boolean smallStatistics = false;

    @Parameter(names = "-f", description = "constraint for full statistic.")
    public Boolean fullStatistics= false;

    public final String integersFileName = "integers.txt";
    public final String stringsFileName = "strings.txt";
    public final String floatsFileName = "floats.txt";
}
