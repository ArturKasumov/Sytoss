//import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileContentTest
{
    String filePath = "src\\test\\resources\\file.txt";
    FileContent workingFile;
    @Before
    public void init()
    {
        workingFile = new FileContent(filePath);
    }

    @Test
    public void fileIsFound() throws FileNotFoundException
    {
        workingFile.openFile(filePath);
        Assert.assertTrue(workingFile.getFile().exists());
    }


    @Test
    public void fileIsNotEmpty() throws IOException
    {
        workingFile.openFile(filePath);
        Assert.assertFalse(workingFile.fileIsEmpty());
    }

}