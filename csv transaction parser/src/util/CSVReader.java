package util;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<String> readLines(String filepath) throws IOException {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }
}