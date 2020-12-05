package Users;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class CsvSearch {
public static boolean end = false;
    BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/Users/users.csv"));
    String line = "";
    String comma = ",";
    public CsvSearch() throws IOException {

    }
    public String[] sendUser() throws IOException {
        while ((line = bufferedReader.readLine()) != null) {

            // use comma as separator
            String[] user = line.split(comma);

                return user;


            //System.out.println(user[0]);
            //System.out.println(country[1]);
        } end = true;
        String[] throwAway = new String[10];
        return throwAway;

}}