import java.io.IOException;

public class Parser {
    public static void main(String[] st) throws IOException {
        FileContent fileContent = new FileContent("src\\main\\resources\\file.txt");

        fileContent.fillLines();
        fileContent.outputLines();
    }
}
