package Users;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CsvSearch {
public static boolean end = false;
    BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/Users/users.csv"));

    String line = "";
    String comma = ",";
    public CsvSearch() throws IOException {

    }
    public String[] sendUser() throws IOException {
        while ((line = bufferedReader.readLine()) != null) {


            String[] user = line.split(comma);

                return user;

        } end = true;
        bufferedReader.close();
        String[] throwAway = new String[9];
        return throwAway;

}
    public void addUser(String[] userData) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/Users/users.csv", true));
        bufferedWriter.append("\n");
        for (int i=0; i<9;i++) {
            bufferedWriter.append(userData[i]);
            if(i<8){
            bufferedWriter.append(",");}
        }
        bufferedWriter.close();

    }

    public void editUser(String[] userData) throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader("src/main/java/Users/users.csv"));
        List<String[]> csvBody = csvReader.readAll();
        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);

            if(strArray[0].equalsIgnoreCase(userData[0])){

                csvBody.get(i)[3] = userData[3];
                csvBody.get(i)[4] = userData[4];
                csvBody.get(i)[5] = userData[5];
                csvBody.get(i)[6] = userData[6];
                csvBody.get(i)[7] = userData[7];
                csvBody.get(i)[8] = userData[8];
                i = csvBody.size();
           }

    }
        csvReader.close();

        // Write to CSV file which is open
        CSVWriter csvWriter = new CSVWriter(new FileWriter("src/main/java/Users/users.csv"), ',',
                CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
        csvWriter.writeAll(csvBody);
        csvWriter.flush();
        csvWriter.close();

        RandomAccessFile file = new RandomAccessFile("src/main/java/Users/users.csv","rw");
        long length = file.length();
        file.setLength(length-1);
        file.close();
}}