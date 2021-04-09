import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class SaxXmlWriter implements XMLWriter{

    private  List<PersonLine> list;
    private  String fileName;

    SaxXmlWriter(List list, String fileName){
        this.list = list;
        this.fileName = fileName;
    }


    public  void writeToXml() throws Exception {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        TransformerFactory tf = TransformerFactory.newInstance();
        if(!tf.getFeature(SAXTransformerFactory.FEATURE)){
            throw new RuntimeException(
                    "Did not find a SAX-compatible TransformerFactory.");
        }
        SAXTransformerFactory stf = (SAXTransformerFactory)tf;
        TransformerHandler th = stf.newTransformerHandler();
        th.setResult(new StreamResult(new FileOutputStream(fileName)));

        th.startDocument();

        AttributesImpl fieldAttrs = new AttributesImpl();
        th.startElement("", "", "persons", null);
        for(PersonLine p : list) {

            fieldAttrs.clear();
            fieldAttrs.addAttribute("", "", "id", "",p.getIDX());
            th.startElement("", "", "person", fieldAttrs);


            fieldAttrs.clear();
            th.startElement("", "", "Name", null);

            fieldAttrs.addAttribute("", "", "FirstName", "",p.getFirstName());
            th.startElement("", "", "FirstName", fieldAttrs);
            th.endElement("", "", "FirstName");

            fieldAttrs.clear();
            fieldAttrs.addAttribute("", "", "SecondName", "",p.getLastName());
            th.startElement("", "", "LastName", fieldAttrs);
            th.endElement("", "", "LastName");

            th.endElement("", "", "Name");

            fieldAttrs.clear();
            fieldAttrs.addAttribute("", "", "date", "",  date.format(p.getBirthDate()));
            th.startElement("", "", "Birthday", fieldAttrs);
            th.endElement("", "", "Birthday");

            fieldAttrs.clear();
            fieldAttrs.addAttribute("", "", "Комментарий", "", p.getComment());
            th.startElement("", "", "Comment", fieldAttrs);
            th.endElement("", "", "Comment");

            th.endElement("", "", "person");
        }
        th.endElement("", "", "persons");
        th.endDocument();
    }
}