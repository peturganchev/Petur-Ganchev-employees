import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class EmployeesMain {
    private static String line = "______________________________________________";

    /**
     * Used to find if 2 coworkers worked together on one project
     * @param emp1 String array of the first employee
     * @param emp2 String array of the second employee
     * @return returns true or false
     */
    private static boolean sameTimeOnProject(String[] emp1, String[] emp2){
        Date emp1Start = DateTimeManipulation.parseDate(emp1[2]);
        Date emp1Finish = DateTimeManipulation.parseDate(emp1[3]);
        Date emp2Start = DateTimeManipulation.parseDate(emp2[2]);
        Date emp2Finish = DateTimeManipulation.parseDate(emp2[3]);
        int project1 = Integer.parseInt(emp1[1]);
        int project2 = Integer.parseInt(emp2[1]);
        boolean sameProject = project1 == project2;

        /* If emp1 starts on same date as emp2, or they finish on the same day - working together
           If emp1 starts before emp2 finishes and emp2 starts before emp1 finishes - working together
         */
        boolean matchingTimeInterval = (emp1Start.compareTo(emp2Start) == 0)||
                (emp1Finish.compareTo(emp2Finish) == 0)||
                ((emp1Start.before(emp2Finish))&&(emp2Start.before(emp1Finish)));

            return (sameProject && matchingTimeInterval);
    }

    /**
     * Used to calculate and print the most time 2 coworkers spend together on same project
     * @param employeeData input the String array of the employers data
     */
    private static void mostTimeTogether (String[][] employeeData){
        long mostDays = 0;
        int employee1 = 0;
        int employee2 = 0;
        for (int i = 0; i < employeeData.length; i++) {
            for (int j = i+1; j < employeeData.length; j++) {
                System.out.printf("i = %d j = %d%n",i,j);
                if (sameTimeOnProject(employeeData[i],employeeData[j])&&
                        (DateTimeManipulation.daysTogether(employeeData[i],employeeData[j])) > mostDays){

                    mostDays = DateTimeManipulation.daysTogether(employeeData[i],employeeData[j]);
                    employee1 = i;
                    employee2 = j;
                }
            }
        }
            if (mostDays > 0){
                System.out.printf("%s worked together with %s on project %s for %d days. %n", employeeData[employee1][0],
                        employeeData[employee2][0],employeeData[employee1][1],mostDays);
            } else {
                System.out.println("No employees worked together.");
            }
        }

    /**
     * used to choose between default file or custom .txt file
     * @return returns String "./src/resources/" + fileName.txt as a path
     */
    private static File chooseTextFile(){
            Scanner scanner = new Scanner(System.in);
            File file;
            String path;
            System.out.println("Press 1 if you want to change employeesData.txt with custom .txt file " +
                    "or 2 if you want to keep the same file.");
            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    System.out.println("place your file in resources package folder and type the name/path of the file (ex. myFile.txt)...");
                    System.out.print("Your file name: ");
                    choice = scanner.nextLine();
                    path = "./src/resources/"+choice;
                    break;
                case "2":
                    path = "./src/resources/employeesData.txt";
                    break;
                    default:
                        System.out.println("Wrong input... Please choose between 1 or 2...");
                        System.out.println(line);
                        return chooseTextFile();
            }
            file = new File(path);
        return file;
        }


    public static void main(String[] args) {
        File file;
        /*
        Got some free time so I added some check in the cases that there is wrong path or wrong file name
         */
        do {
            file = chooseTextFile();
            if (!file.exists()){
                System.out.println("There is no such File!");
                System.out.println(line);
            }
        }while (!file.exists());

        String[][] employeeData = ReadInputFile.readFile(file);
        mostTimeTogether(employeeData);
    }
}
