import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXReaderTest
{


    @Test
    public void rightFormat() throws Exception
    {
        SAXReader reader = new SAXReader("src\\test\\resources\\file.xml");
        List<Line> linesFromFile;
        linesFromFile = reader.read();
        List<Line> lines = new ArrayList<>();
        lines.add(new PersonLine("1,Artur,Kasumov,\"2001-08-23\",Комментарий"));
        lines.add(new PersonLine("2,Lexa,Ishchenko,\"2002-08-04\",Крутой чел"));
        lines.add(new PersonLine("3,Misha,Rybalka,\"2000-05-19\",Мясорубка"));

        for (int i = 0; i < linesFromFile.size() ; ++i )
        {
            Assert.assertEquals(((PersonLine)linesFromFile.get(i)).getIDX(),((PersonLine)lines.get(i)).getIDX());
            Assert.assertEquals(((PersonLine)linesFromFile.get(i)).getFirstName(),((PersonLine)lines.get(i)).getFirstName());
            Assert.assertEquals(((PersonLine)linesFromFile.get(i)).getLastName(),((PersonLine)lines.get(i)).getLastName());
            Assert.assertEquals(((PersonLine)linesFromFile.get(i)).getBirthDate(),((PersonLine)lines.get(i)).getBirthDate());
            Assert.assertEquals(((PersonLine)linesFromFile.get(i)).getComment(),((PersonLine)lines.get(i)).getComment());
        }
    }


    @Test(expected = XMLFormatException.class)
    public void wrongFormat() throws Exception
    {
        SAXReader reader = new SAXReader("src\\test\\resources\\file1.xml");
        List<Line> linesFromFile;

        linesFromFile = reader.read();

    }

    @Test(expected = IOException.class)
    public void noFile() throws Exception
    {
        SAXReader reader = new SAXReader("notExistsFile.xml");
        List<Line> linesFromFile;

        linesFromFile = reader.read();

    }

    @Test
    public void emptyFile() throws Exception
    {
        SAXReader reader = new SAXReader("src\\test\\resources\\emptyFile.xml");
        List<Line> linesFromFile;

        linesFromFile = reader.read();
        Assert.assertNull(linesFromFile);
    }
}