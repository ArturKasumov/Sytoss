import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import Reader.*;
import Writer.*;

public class DataConverter {
    private static final Logger LOG = Logger.getLogger(DataConverter.class.getName());
    private final String inputFilepath;
    private final String outputFilepath;

    DataConverter(String filePathFrom, String filePathTo) {
        inputFilepath = filePathFrom;
        outputFilepath = filePathTo;
    }

    public void convert() throws Exception {
        List<Line> list = null;
        int maxJDOMFileLength = 20480;

        Reader reader = null;
        if (inputFilepath.contains(".csv")) {
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
        } else {
            warning();
        }


        Writer writer = null;
        if (outputFilepath.contains(".csv")) {
            writer = new CSVWriter(outputFilepath);

        } else if (outputFilepath.contains(".xml")) {
            if (list.size() > 4) {
                writer = new SaxWriter(list, outputFilepath);

            } else {
                writer = new JDOMWriter(list, outputFilepath);

            }
        } else {
            warning();
        }

        writer.write(list);

    }

    private void warning() {
        LOG.severe("No such type.\n You should use [.txt] or [.xml]");
        System.exit(-2);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            LOG.severe("You should have 2 args");
            System.exit(-1);
        }
        DataConverter dataConverter = new DataConverter(args[0], args[1]);
        dataConverter.convert();
    }

}
