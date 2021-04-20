import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JDOMReader extends Reader {
    private String filePath;

    JDOMReader(String filePath) {
        this.filePath = filePath;
        lines = new ArrayList<>();
    }

    @Override
    List<Line> read() {
        SAXBuilder sax = new SAXBuilder();
        try {
            Document doc = sax.build(new File(filePath));
            Element rootNode = doc.getRootElement();
            List<Element> listOfElements = rootNode.getChildren("person");
            for (Element target : listOfElements) {
                String id = target.getAttributeValue("id");
                String firstName = target.getChild("Name").getChildText("FirstName");
                String lastName = target.getChild("Name").getChildText("LastName");
                String birthdayDate = target.getChild("Birthday").getAttributeValue("date");
                String comment = target.getChildText("Comment");
                StringBuilder sb = new StringBuilder();
                sb.append(id).append(",").append(firstName).append(",").append(lastName).append(",")
                        .append(birthdayDate).append(",").append(comment);
                lines.add(new PersonLine(sb.toString()));
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
