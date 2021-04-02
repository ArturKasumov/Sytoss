import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface LineInterface {
    default String getIDX() {
      return "";
    }

    default String getFirstName() {
        return "";
    }

    default String getLastName() {
        return "";
    }

    default Date getBirthDate() throws Exception {
        return new Date();
    }

    default String getComment() {
        return "";
    }
    default List<String> getCells() {
        return new ArrayList<>();
    }
}
