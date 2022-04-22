// import java.io.FileNotFoundException;  // Needed in Java 1.7, not 1.8?
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
// import com.opencsv.exceptions.CsvException;  // Needed in 1.7, not 1.8?

public class Main {

    static final boolean CheckCVS = true;  // set to false if you don't want to bother with the CSV self-check

    public static void main(String[] args) {
        if (CheckCVS) {
            System.out.println("Checking CSV reader opens one of the files (change CheckCVS variable in Main to turn this off):");
            try {
                CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader("vote_files/SWVF_1_22_short.txt"));
                System.out.println("   Cool, opened a voter list CSV, looks like the libraries are configured right.");
                ArrayList<String[]> voterStringList = new ArrayList<String[]>(reader.readAll());
                reader.close();
                System.out.println("   Cool, extracted " + voterStringList.size() + " lines of CSV data from the CSV file.");
                System.out.println("   The first of which was: " + Arrays.toString(voterStringList.get(0)));
            } catch (IOException e) {
                System.out.println("   ******* Oops, something went wrong when opening the vote file :-(");
                e.printStackTrace();
            } catch (CsvException e) {
                System.out.println("   ******* Oops, something went wrong when extracting CSV entries :-(");
                e.printStackTrace();
            }
            System.out.println();
        }


        System.out.println("Put your main program here, as per the README file.");
    }
}
