import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class readInputFile {

    /**
     *  Used to Parse the .txt file to an 2D array with rows ( different people) and cols ( 0: EmpID; 1:ProjectID;
     *  2: DateFrom; 3: DateTo;).
     * @param file input a File type
     * @return returns an 2D array
     */
    public static String[][] readFile(File file){
        Scanner fileReader = null;
        String[][] employeeData;
        try {
            fileReader = new Scanner(file);
            ArrayList<ArrayList<String>> emData = new ArrayList<ArrayList<String>>();
            while (fileReader.hasNextLine() ){
                String[] line = fileReader.nextLine().split("[, ]+");
                ArrayList<String> listLine = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    listLine.add(line[i]);
                }
                emData.add(listLine);
            }
            emData.trimToSize();

            employeeData = new String[emData.size()][4];
            for (int i = 0; i < emData.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    employeeData[i][j] = emData.get(i).get(j);
                }
            }

            return employeeData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileReader.close();

        return new String[1][1];
    }
}
