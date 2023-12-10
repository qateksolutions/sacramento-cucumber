package utilities;

import java.io.File;
import java.util.Scanner;

public class CsvConnector {
    public static String[] readCsvData(String filePath) throws Exception {
        Scanner sc = null;
        StringBuilder data = new StringBuilder();
        try {
            sc = new Scanner(new File(filePath));
            sc.useDelimiter("");
            while (sc.hasNext()) {
                data.append(sc.next());
            }
        } catch (Exception e) {
            throw new Exception("CSV File reading exception is: " + e.getMessage());
        } finally {
            sc.close();
        }
        return data.toString().split(System.lineSeparator());
    }
}
