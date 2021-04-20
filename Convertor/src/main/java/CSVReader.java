import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends Reader {
    private File file;
    private String filePath;

    CSVReader(String filePath) {
        this.filePath = filePath;
        lines = new ArrayList<>();
    }

    private void openFile() {
        file = new File(filePath);
    }

    public void fillLines(EnumForLines type) throws IOException {
        String s = null;

        try (BufferedReader f = new BufferedReader(new FileReader(file))) {
            while ((s = f.readLine()) != null) {
                Line toReturn = null;
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
    List<Line> read() {
        openFile();
        try {
            fillLines(EnumForLines.PERSONLINE);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lines;
    }
}
