import Reader.Line;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LineTest
{
    @Test
    public void convertCommonFormat()
    {
        String testingLine = "Artur,19,Kharkiv";
        Line workingLine = new Line(testingLine);
        Assert.assertEquals("Artur 19 Kharkiv", String.join(" ", workingLine.getCells()));
    }

    @Test
    public void convertUncommonFormat()
    {
        String testingLine = "Привет,\"м/р\"";
        Line workingLine = new Line(testingLine);

        Assert.assertEquals("Привет м/р", String.join(" ", workingLine.getCells()));
    }

    @Test
    public void convertCommas()
    {
        String testingLine = "Привет,19,\"м,ир\"";
        Line workingLine = new Line(testingLine);

        Assert.assertEquals("Привет 19 м,ир", String.join(" ", workingLine.getCells()));
    }

    @Test
    public void convertQuotes()
    {
        String testingLine = "Привет,19,\"парк\"\"Название\"\"";
        Line workingLine = new Line(testingLine);

        Assert.assertEquals("Привет 19 парк\"Название\"", String.join(" ", workingLine.getCells()));
    }

    @Test
    public void commasAndQuotesLastQuote()
    {
        String testingLine = "Artur,19,\"Клуб\"\"Как,дела\"\"\",\"При,в\"";
        Line workingLine = new Line(testingLine);

        Assert.assertEquals("Artur 19 Клуб\"Как,дела\" При,в",
                String.join(" ", workingLine.getCells()));
    }

}