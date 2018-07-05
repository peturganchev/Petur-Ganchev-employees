import java.io.File;
import java.util.Date;

public class employeesMain {

    /**
     * Used to find if 2 coworkers worked together on one project
     * @param emp1 String array of the first employee
     * @param emp2 String array of the second employee
     * @return returns true or false
     */
    private static boolean sameTimeOnProject(String[] emp1, String[] emp2){
        Date emp1Start = dateTimeManipulation.parseDate(emp1[2]);
        Date emp1Finish = dateTimeManipulation.parseDate(emp1[3]);
        Date emp2Start = dateTimeManipulation.parseDate(emp2[2]);
        Date emp2Finish = dateTimeManipulation.parseDate(emp2[3]);
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
            for (int j = i+1; j < 4; j++) {
                if (sameTimeOnProject(employeeData[i],employeeData[j])&&
                        (dateTimeManipulation.daysTogether(employeeData[i],employeeData[j])) > mostDays){

                    mostDays = dateTimeManipulation.daysTogether(employeeData[i],employeeData[j]);
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

    public static void main(String[] args) {
        File file = new File("./src/resources/employeesData.txt");
        String[][] employeeData = readInputFile.readFile(file);
        mostTimeTogether(employeeData);
    }
}
