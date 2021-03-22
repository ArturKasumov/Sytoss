import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConvertCSVTest {

    ConvertCSV convert = new ConvertCSV();
    @Test
    public void convertCommonFormat()
    {
        Assert.assertEquals("Artur 19 Kharkiv", convert.convertStandartFortmat("Artur,19,Kharkiv"));
    }

    @Test
    public void convertUncommonFormat()
    {
        Assert.assertEquals("Привет м/р", convert.convertUnstandartFortmat("Привет, \"м/р\""));
    }

    @Test
    public void convertCommas()
    {
        Assert.assertEquals("Привет 19 м,ир", convert.convertWithCommas("Привет,19,\"м,ир\""));
    }
    @Test
    public void convertQuotes()
    {
        Assert.assertEquals("Привет 19 парк\"Название\"", convert.convertWithQuotes("Привет,19,\"парк\"\"Название\"\""));
    }


    @Test
    public void fileIsFound() throws FileNotFoundException
    {
        String filePath = "src\\main\\resources\\file.txt";
        convert.openFile(filePath);
        Assert.assertTrue(convert.getFile().exists());
    }


    @Test
    public void fileIsEmpty() throws IOException {
        String filePath = "src\\main\\resources\\file.txt";
        convert.openFile(filePath);
        Assert.assertTrue(convert.fileIsEmpty());
    }

}

