import java.util.ArrayList;
import java.util.List;


public class Line {
    List<String> cells = new ArrayList<String>();

    Line(String pStr) {
        separateLineIntoWords(pStr);
    }

    public List<String> getCells() {
        return cells;
    }

    public void separateLineIntoWords(String line) {
        int idBegin = 0;
        int idEnd = 0;

        int idx = 0;
        boolean isQuoteOpened = false;
        StringBuilder builder = new StringBuilder();
        ;
        while (idx < line.length()) {

            if (line.charAt(idx) == '"') {

                if (idx < line.length() - 1 && line.charAt(idx + 1) == '"') {
                    builder.append(line.charAt(idx));
                    ++idx;
                } else {
                    isQuoteOpened = !isQuoteOpened;
                }

            } else if (line.charAt(idx) == ',') {
                if (isQuoteOpened) {
                    builder.append(line.charAt(idx));
                } else {
                    cells.add(builder.toString());
                    builder.delete(0, builder.length());
                }

            } else {
                builder.append(line.charAt(idx));
            }
            ++idx;
        }
        cells.add(builder.toString());
    }
}
