import java.io.File;

public class employeesMain {
    public static void main(String[] args) {
        File file = new File("./src/resources/employeesData.txt");
        String[][] employeeData = readInputFile.readFile(file);
        boolean sameProject = false;

        for (String[] a : employeeData)
        {
            System.out.print(a[0]+" ");
            System.out.print(a[1]+" ");
            System.out.print(a[2]+" ");
            System.out.print(a[3]+" ");
            System.out.println("");

        }

    }
}
