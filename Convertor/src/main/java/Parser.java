
import java.util.List;
import java.util.regex.Pattern;


public class Parser {

    Parser(String filePathFrom, String filePathTo, EnumForLines enam) throws Exception {
        FileContent fileContent = new FileContent(filePathFrom);
        fileContent.fillLines(enam);
        List<Line> list = fileContent.getLines();
        if (enam == EnumForLines.PERSONLINE) {
            if (list.size() > 20) {
                SaxXmlWriter writerXML = new SaxXmlWriter(list, filePathTo);
                writerXML.writeToXml();
            } else {
                WriterXML writerXML = new WriterXML(list, filePathTo);
                writerXML.writeToXml();
            }
        } else {
            System.err.println("To parse file to .xml you should use PERSONLINE type");
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 2 || !Pattern.compile(".*csv").matcher(args[0]).find()
                || !Pattern.compile(".*xml").matcher(args[1]).find()) {
            System.err.println("You should have 2 arguments:\n" +
                    "First must be [filepath to .txt file]\n" +
                    "Second must be [filepath to .xml file]");
            return;
        }

        new Parser(args[0], args[1], EnumForLines.PERSONLINE);

    }

}
