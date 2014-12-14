package se.lu.bosmp.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Turns a List of date ordered files into a List of log entry strings.
 */
public class FileProcessor implements Processor<String, File> {

    public List<String> process(List<File> files) {
        StringBuffer buf = new StringBuffer();
        BufferedReader br = null;
        try {
            for(File f : files) {

                br = new BufferedReader(new FileReader(f));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    buf.append(sb.toString());
                } finally {
                    br.close();
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return Arrays.asList(buf.toString().split("\r\n"));
    }

}
