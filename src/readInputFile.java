import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readInputFile {

    public static String[][] readFile(File file){
        Scanner fileReader = null;
        String[][] employeeData = new String[4][];
        try {
            fileReader = new Scanner(file);
            int index = 0;

            while (fileReader.hasNextLine() && index < employeeData.length) {
                employeeData[index] = fileReader.nextLine().split("[, ]+"); //split returns an array
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileReader.close();

        return employeeData;
    }
}
