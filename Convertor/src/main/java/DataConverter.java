import java.io.File;
import java.util.List;

public class DataConverter {
    private String inputFilepath;
    private String outputFilepath;

    DataConverter(String filePathFrom, String filePathTo) throws Exception {
        inputFilepath = filePathFrom;
        outputFilepath = filePathTo;
    }

    public void convert() throws Exception {
        List<Line> list = null;
        int maxJDOMFileLength = 20480;
        Reader reader = null;
        if (inputFilepath.contains(".txt")) {
            reader = new CSVReader(inputFilepath);
            list = reader.read();

        } else if (inputFilepath.contains(".xml")) {
            if (new File(inputFilepath).length() < maxJDOMFileLength) {
                reader = new JDOMReader(inputFilepath);
                list = reader.read();
            } else {
                reader = new SAXReader(inputFilepath);
                list = reader.read();
            }

        }


        Writer writer = null;
        if (outputFilepath.contains(".txt")) {
            writer = new CSVWriter(outputFilepath);

        } else if (outputFilepath.contains(".xml")) {
            if (list.size() > 4) {
                writer = new SaxWriter(list, outputFilepath);

            } else {
                writer = new JDOMWriter(list, outputFilepath);

            }
        } else {
            System.err.println("No such type.\n You should use [.txt] or [.xml]");
        }
        if (writer != null) {
            writer.write(list);
        }
    }

    public static void main(String[] args) throws Exception {
        DataConverter dataConverter = new DataConverter(args[0], args[1]);
        dataConverter.convert();

    }

}
