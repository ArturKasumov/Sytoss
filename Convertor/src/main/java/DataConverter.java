
import java.io.IOException;
import java.util.List;


public class DataConverter {


    private String inputFilepath;
    private String outputFilepath;
    DataConverter(String filePathFrom, String filePathTo, EnumForLines enam) throws Exception {
        inputFilepath = filePathFrom;
        outputFilepath = filePathTo;

    }

    public void convert() throws Exception {
        List<Line> list = null;
        //if (enam == EnumForLines.PERSONLINE) {
        Reader reader;
        if(inputFilepath.contains(".txt"))
        {
            reader = new CSVReader(inputFilepath);
            list = reader.read();
        }

        Writer writer = null;
        if ( outputFilepath.contains(".txt") )
        {
            writer = new CSVWriter(outputFilepath);

        }
        else if( outputFilepath.contains(".xml") )
        {
            if (list.size() > 20) {
                writer = new SaxWriter(list, outputFilepath);

            } else {
                writer = new JDOMWriter(list, outputFilepath);

            }
        }
        else
        {
            System.err.println("No such type.\n You should use [.txt] or [.xml]");
        }
        if( writer != null)
        {
            writer.write(list);
        }


        /*} else {
            System.err.println("To parse file to .xml you should use PERSONLINE type");
        }*/

    }

    public static void main(String[] args) throws Exception {
       /* if (args.length != 2 || !Pattern.compile(".*csv").matcher(args[0]).find()
                || !Pattern.compile(".*xml").matcher(args[1]).find()) {
            System.err.println("You should have 2 arguments:\n" +
                    "First must be [filepath to .txt file]\n" +
                    "Second must be [filepath to .xml file]");
            return;
        }*/

        DataConverter dataConverter = new DataConverter(args[0], args[1], EnumForLines.PERSONLINE);
        dataConverter.convert();

    }

}
