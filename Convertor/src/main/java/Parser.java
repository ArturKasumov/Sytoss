import java.io.IOException;
import java.util.*;

public class Parser {
    public static void main(String[] st) throws IOException {
        FileContent fileContent = new FileContent("src\\main\\resources\\file.txt");
        fileContent.fillLines(Enam.PERSONLINE);
        System.out.println(fileContent.getLines().get(0));
        fileContent.outputLines();
    }
}
