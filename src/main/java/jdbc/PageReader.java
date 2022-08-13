package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PageReader {
    BufferedReader reader;

    public PageReader() {
    }

    public String readFile(File file) throws IOException {
        reader = new BufferedReader(new FileReader(file));
        StringBuilder answer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            answer.append(line);
        }
        return answer.toString();
    }
}
