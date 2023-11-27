package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public List<String> readFromFile(String fileName) {
        List<String> rows = new ArrayList<>();

        InputStream inputStream = getFileAsIOStream(fileName);
        try (InputStreamReader isr = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rows;
    }

    private InputStream getFileAsIOStream(final String fileName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
