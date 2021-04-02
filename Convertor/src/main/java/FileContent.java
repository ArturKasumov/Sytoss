import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileContent implements LineInterface {

    private File file;
    private List<LineInterface> lines = new ArrayList<>();

    FileContent(String pFileName) {
        file = new File(pFileName);
    }

    FileContent(String pFileName, String className) {
        file = new File(pFileName);

    }

    public File getFile() {
        return file;
    }

    public boolean fileIsEmpty() throws IOException {
        fillLines(Enam.LINE);
        return lines.size() == 0;
    }

    public void openFile(String filePath) {
        file = new File(filePath);
    }

    public List<LineInterface> getLines() {
        return lines;
    }

    public void outputLines() {
        for (int idx1 = 0; idx1 < lines.size(); idx1++) {
            for (int idx2 = 0; idx2 < lines.get(idx1).getCells().size(); idx2++)
                System.out.print(lines.get(idx1).getCells().get(idx2) + " ");
            System.out.println("");
        }
    }

    public void fillLines(Enam type) throws IOException {
        String s = null;

        try (BufferedReader f = new BufferedReader(new FileReader(file))) {
            while ((s = f.readLine()) != null) {
                LineInterface toReturn = null;
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
}
