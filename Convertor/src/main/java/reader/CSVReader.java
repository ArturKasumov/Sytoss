package reader;

import lines.EnumForLines;
import lines.Line;
import lines.PersonLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVReader extends Reader {
    private File file;
    private final String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
        lines = new ArrayList<>();
    }

    public void openFile() {
        file = new File(filePath);
    }

    public void fillLines(EnumForLines type) throws IOException {
        String s;

        try (BufferedReader f = new BufferedReader(new FileReader(file))) {
            while ((s = f.readLine()) != null) {
                Line toReturn;
                switch (type) {
                    case PERSONLINE:
                        toReturn = new PersonLine(s);
                        break;
                    case LINE:
                        toReturn = new Line(s);
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong line type:" + type);
                }
                lines.add(toReturn);
            }
        }
    }

    @Override
    public List<Line> read() throws Exception {
        openFile();
        fillLines(EnumForLines.PERSONLINE);
        return Collections.unmodifiableList(lines);
    }
}
