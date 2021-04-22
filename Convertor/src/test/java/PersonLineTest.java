
import lines.PersonLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonLineTest
{
    private enum IdxForTestingPersonLine { IDX, FIRSTNAME, LASTNAME, BIRTHDATE, COMMENT }

    @Test
    public void personalDataTest() throws Exception
    {
        String[] expectedPersonalData = {"0", "Petro", "Sergiyovych", "Bulba", "06.12.1985", "\"Ukrainian\""};
        PersonLine actualPersonDataList = new PersonLine("0,Petro,Sergiyovych,Bulba,\"06.12.1985\",\"\"\"Ukrainian\"\"\"");

        assertEquals(expectedPersonalData[IdxForTestingPersonLine.IDX.ordinal()], actualPersonDataList.getIDX());
        assertEquals(expectedPersonalData[IdxForTestingPersonLine.FIRSTNAME.ordinal()], actualPersonDataList.getFirstName());
        assertEquals(expectedPersonalData[IdxForTestingPersonLine.LASTNAME.ordinal()], actualPersonDataList.getLastName());
        assertEquals(expectedPersonalData[IdxForTestingPersonLine.BIRTHDATE.ordinal()], actualPersonDataList.getBirthDate());
        assertEquals(expectedPersonalData[IdxForTestingPersonLine.COMMENT.ordinal()], actualPersonDataList.getComment());
    }
}