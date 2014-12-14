package se.lu.bosmp.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class SingleFileProcessor {

    public String process(File f) {
        StringBuilder buf = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
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
            return buf.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unparsable file: " + f.getName());
        }
    }
}
