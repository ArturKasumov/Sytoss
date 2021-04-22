import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import lines.Line;
import reader.*;
import writer.*;

public class DataConverter {
    private static final Logger LOG = Logger.getLogger(DataConverter.class.getName());
    private final String inputFilepath;
    private final String outputFilepath;

    public DataConverter(String filePathFrom, String filePathTo) {
        inputFilepath = filePathFrom;
        outputFilepath = filePathTo;
    }

    public void convert() {
        List<Line> list = null;
        int maxJDOMFileLength = 20480;

        Reader reader;
        if (inputFilepath.contains(".csv")) {
            reader = new CSVReader(inputFilepath);

        } else if (inputFilepath.contains(".xml")) {

            if (new File(inputFilepath).length() < maxJDOMFileLength) {
                reader = new JDOMReader(inputFilepath);
            } else {
                reader = new SAXReader(inputFilepath);
            }

        } else {
            throw new IllegalArgumentException();
        }

        try {
            list = reader.read();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        Writer writer;
        if (outputFilepath.contains(".csv")) {
            writer = new CSVWriter(list,outputFilepath);

        } else if (outputFilepath.contains(".xml")) {

            if (list.size() > 4) {
                writer = new SaxWriter(list, outputFilepath);
            } else {
                writer = new JDOMWriter(list, outputFilepath);
            }

        } else {
            throw new IllegalArgumentException();
        }

        try {
            writer.write();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            LOG.severe("You should have 2 args");
            System.exit(-1);
        }
        DataConverter dataConverter = new DataConverter(args[0], args[1]);
        try {
            dataConverter.convert();
        } catch (IllegalArgumentException exception) {
            LOG.severe("No such type.\n You should use [.txt] or [.xml]");
            exception.printStackTrace();
        }
    }

}
