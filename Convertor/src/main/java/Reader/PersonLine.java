package Reader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonLine extends Line {
    private enum IdxForPersonLine {IDX, FIRSTNAME, LASTNAME, BIRTHDATE, COMMENT}

    public PersonLine(String pStr) {
        super(pStr);
    }

    public String getIDX() {
        String personIdx = getCells().get(IdxForPersonLine.IDX.ordinal());
        return personIdx;
    }

    public String getFirstName() {
        String personFirstName = getCells().get(IdxForPersonLine.FIRSTNAME.ordinal());
        return personFirstName;
    }

    public String getLastName() {
        String personLastName = getCells().get(IdxForPersonLine.LASTNAME.ordinal());
        return personLastName;
    }

    public Date getBirthDate() throws Exception {
        String personBirthDate = getCells().get(IdxForPersonLine.BIRTHDATE.ordinal());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.parse(personBirthDate);
    }

    public String getComment() {
        String personComment = getCells().get(IdxForPersonLine.COMMENT.ordinal());
        return personComment;
    }
}
