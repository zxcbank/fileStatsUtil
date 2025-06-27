package kkkombinator.Configuration;

import com.beust.jcommander.IStringConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListConverter implements IStringConverter<List<File>> {
    @Override
    public List<File> convert(String files) {
        String [] paths = files.split("\n");
        List<File> fileList = new ArrayList<>();
        for(String path : paths){
            fileList.add(new File(path));
        }
        return fileList;
    }
}
