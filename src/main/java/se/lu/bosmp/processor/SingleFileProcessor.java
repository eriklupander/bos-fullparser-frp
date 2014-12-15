package se.lu.bosmp.processor;

import se.lu.bosmp.scanner.FileData;

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

    public FileData process(File f) {
        FileData fileData = new FileData();
        fileData.setFileName(f.getName());
        fileData.setFileNameHash(normalize(f.getName()));
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
            fileData.setData(buf.toString());
            return fileData;
        } catch (IOException e) {
            throw new IllegalArgumentException("Unparsable file: " + f.getName());
        }
    }

    private Integer normalize(String fileName) {
        fileName = fileName
                .replaceAll("-", "")
                .replaceAll("_", "");
        fileName = fileName.substring(0, fileName.lastIndexOf("[")-1);
        return fileName.hashCode();
    }
}
