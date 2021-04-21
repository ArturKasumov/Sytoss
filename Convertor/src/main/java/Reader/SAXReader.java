package Reader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class SAXReader extends Reader {
    private String filePath;

    public SAXReader(String filePath) {
        this.filePath = filePath;
        lines = new ArrayList<>();
    }

    @Override
    public List<Line> read() throws Exception {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        File inputFile = new File(filePath);
        if (inputFile.exists() & inputFile.length() == 0) {
            return null;
        }
        xpp.setInput(new FileReader(inputFile));


        int eventType = xpp.getEventType();
        StringBuilder line = new StringBuilder();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {

                if (xpp.getName().equals("person")) {
                    line.append(xpp.getAttributeValue("", "id"));
                    line.append(",");
                } else if (xpp.getName().equals("FirstName")) {
                    line.append(xpp.nextText());
                    line.append(",");
                } else if (xpp.getName().equals("LastName")) {
                    line.append(xpp.nextText());
                    line.append(",");
                } else if (xpp.getName().equals("Birthday")) {
                    line.append(xpp.getAttributeValue("", "date"));
                    line.append(",");
                } else if (xpp.getName().equals("Comment")) {
                    line.append(xpp.nextText());
                } else if (xpp.getName().equals("persons") | xpp.getName().equals("Name")) {

                } else {
                    throw new XMLFormatException("Wrong format");
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                if (xpp.getName().equals("person")) {
                    lines.add(new PersonLine(line.toString()));
                    line.delete(0, line.length());
                }
            }
            eventType = xpp.next();
        }

        /*} catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }*/
        return lines;
    }

}