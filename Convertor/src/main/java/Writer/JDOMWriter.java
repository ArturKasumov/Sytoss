package Writer;

import Reader.Line;
import Reader.PersonLine;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class JDOMWriter extends Writer {

    private List<PersonLine> list;
    private String fileName;

    public JDOMWriter(List list, String fileName) {
        this.list = list;
        this.fileName = fileName;
    }


    @Override
    public void write(List<Line> lines) throws Exception {
        Document doc = new Document();
        doc.setRootElement(new Element("persons"));
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        for (PersonLine p : list) {
            Element person = new Element("person");
            person.setAttribute("id", p.getIDX());
            person.addContent(new Element("Name")
                    .addContent(new Element("FirstName").setText(p.getFirstName()))
                    .addContent(new Element("LastName").setText(p.getLastName())));
            person.addContent(new Element("Birthday").setAttribute("date", date.format(p.getBirthDate())));
            person.addContent(new Element("Comment").setText(p.getComment()));
            doc.getRootElement().addContent(person);
        }
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileOutputStream(fileName));
    }
}
