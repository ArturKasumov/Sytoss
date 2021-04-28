package writer;

import lines.PersonLine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVWriter extends Writer {
    private final String filePath;
    private final List<PersonLine> list;

    public CSVWriter(List list, String filePath) {
        this.list = list;
        this.filePath = filePath;
    }


    @Override
    public void write() throws IOException {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (PersonLine person : list) {
                for (int j = 0; j < person.getCells().size(); ++j) {
                    writer.write(format(person.getCells().get(j)));
                    if (j != person.getCells().size() - 1)
                        writer.write(",");
                    if (j == person.getCells().size() - 1)
                        writer.write(System.lineSeparator());
                }
            }
        }
    }

    private String format(String value) {
        Pattern pattern = Pattern.compile("[/.=\\-;:(){}\\[\\]]");
        Matcher matcher = pattern.matcher(value);
        StringBuilder sb = new StringBuilder();
        if (matcher.find()) {
            sb.append("\"").append(value).append("\"");
            return sb.toString();
        }
        return value;
    }


}
