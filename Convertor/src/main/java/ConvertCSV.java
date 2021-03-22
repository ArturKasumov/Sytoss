import java.io.*;
import java.util.ArrayList;

public class ConvertCSV {

    private File file;

    public File getFile() {
        return file;
    }

    public void openFile(String filePath) {
        file = new File(filePath);
    }

    public String convertStandartFortmat(String csvString) {
        String[] arrayString = csvString.split(",");
        return String.join(" ", arrayString);
    }

    public String convertUnstandartFortmat(String pStr) {
        String[] ArrayString = pStr.split(",");
        for (int idx = 0; idx < ArrayString.length; idx++) {
            ArrayString[idx] = ArrayString[idx].trim();
            if (ArrayString[idx].charAt(0) == '\"')
                ArrayString[idx] = ArrayString[idx].substring(1, ArrayString[idx].length() - 1);

        }

        return String.join(" ", ArrayString);
    }

    public String convertWithCommas(String pStr) {
        {
            boolean isQuoteOpened = false;
            StringBuilder resultStr = new StringBuilder();

            for(int idx = 0; idx < pStr.length(); idx++)
            {
                if(pStr.charAt(idx) == '\"')
                {
                    if(!isQuoteOpened)
                        isQuoteOpened = true;
                    else isQuoteOpened = false;
                }
                else if(pStr.charAt(idx) == ',')
                {
                    if(!isQuoteOpened)
                        resultStr.append(" ");
                    else resultStr.append(',');
                }
                else if(pStr.charAt(idx) == ' ')
                {
                    resultStr.append("");
                }
                else resultStr.append(pStr.charAt(idx));
            }
            return resultStr.toString();
        }
    }

    public String convertWithQuotes(String csvString) {

        String[] ArrayString = csvString.split(",");
        for (int idx = 0; idx < ArrayString.length; idx++) {
            ArrayString[idx] = ArrayString[idx].trim();
            if (ArrayString[idx].charAt(0) == '\"') {
                ArrayString[idx] = ArrayString[idx].substring(1, ArrayString[idx].length() - 1);
                for (int i = 0; i < ArrayString[idx].length()-1; ++i) {
                    if (ArrayString[idx].charAt(i) == '\"' && ArrayString[idx].charAt(i + 1) == '\"')
                        ArrayString[idx] = ArrayString[idx].substring(0, i) + ArrayString[idx].substring(i + 1, ArrayString[idx].length());
                }
            }
        }
        return String.join(" ", ArrayString);
    }

    public String convertWithQuotesAndCommas(String pStr) {

        boolean isQuoteOpened = false;
        StringBuilder resultStr = new StringBuilder();

        for (int idx = 0; idx < pStr.length(); idx++) {
            if (pStr.charAt(idx) == '\"') {
                if (!isQuoteOpened)
                    isQuoteOpened = true;
                else isQuoteOpened = false;
            } else if (pStr.charAt(idx) == ',') {
                if (!isQuoteOpened)
                    resultStr.append(" ");
                else resultStr.append(',');
            } else if (pStr.charAt(idx) == ' ') {
                resultStr.append("");
            } else resultStr.append(pStr.charAt(idx));
        }
        return resultStr.toString();


    }

    public ArrayList readFile()  throws IOException {

        ArrayList list = new ArrayList();
        String s = null;
        try (BufferedReader f = new BufferedReader(new FileReader(file))) {
            while ((s = f.readLine())!=null){
                list.add(s);
            }
        }
        return list;
    }

    public boolean fileIsEmpty()  throws IOException {
        return readFile().size()==0;
    }
}
