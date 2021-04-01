
public class PersonLine extends Line {
    private enum IdxForPersonLine {IDX, FIRSTNAME, SECONDNAME, LASTNAME, BIRTHDATE, COMMENT}

    PersonLine(String pStr) {
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

    public String getSecondName() {
        String personSecondName = getCells().get(IdxForPersonLine.SECONDNAME.ordinal());
        return personSecondName;
    }

    public String getLastName() {
        String personLastName = getCells().get(IdxForPersonLine.LASTNAME.ordinal());
        return personLastName;
    }

    public String getBirthDate() {
        String personBirthDate = getCells().get(IdxForPersonLine.BIRTHDATE.ordinal());
        return personBirthDate;
    }

    public String getComment() {
        String personComment = getCells().get(IdxForPersonLine.COMMENT.ordinal());
        return personComment;
    }
}
