import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVWriter extends Writer{
    private File file;
    private String filePath;

    CSVWriter(String filePath){
        this.filePath = filePath;
    }

    private void openFile(){
        file = new File(filePath);
    }

    @Override
    void write(List<Line> lines) throws Exception {
        openFile();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(Line person : lines){
                for(int j=0;j<person.getCells().size();++j){
                    writer.write(format(person.getCells().get(j)));
                    if(j!=person.getCells().size()-1)
                        writer.write(",");
                    if(j==person.getCells().size()-1)
                        writer.write(System.lineSeparator());
                }
            }
        }
    }

    private String format(String value){
        Pattern pattern = Pattern.compile("[/.=\\-;:(){}\\[\\]]");
        Matcher matcher = pattern.matcher(value);
        StringBuilder sb = new StringBuilder();
        if(matcher.find()){
            sb.append("\"").append(value).append("\"");
            return sb.toString();
        }
        return value;
    }


}
