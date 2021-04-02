
import java.util.List;


public class Parser {
    public static void main(String[] args) throws Exception {
        if (args.length!=2) {
            System.out.println("You should have 2 arguments:\n" +
                    "First must be [filepath to .txt file]\n" +
                    "Second must be [filepath to .xml file]");
            return;
        }
        Enam enam = Enam.PERSONLINE;
        FileContent fileContent = new FileContent(args[0]);
        fileContent.fillLines(enam);
        //fileContent.outputLines();
        List<LineInterface> list;
        list = fileContent.getLines();
        String fileName = args[1];
        if (enam == Enam.PERSONLINE) {
            WriterXML writerXML = new WriterXML(list, fileName);
            writerXML.writeFileUsingJDOM();
        }
    }

}
