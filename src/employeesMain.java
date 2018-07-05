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
        boolean sameProject = emp1[1].equals(emp2[1]);

        /* If emp1 starts on same date as emp2, or they finish on the same day - working together
           If emp1 starts before emp2 finishes and emp2 starts before emp1 finishes - working together
         */
        boolean matchingTimeInterval = (emp1Start.compareTo(emp2Start) == 0)||
                (emp1Finish.compareTo(emp2Finish) == 0)||
                ((emp1Start.before(emp2Finish))&&(emp2Start.before(emp1Finish)));

        System.out.println("sameProject && matchingTimeInterval" + sameProject +" "+ matchingTimeInterval);
            return sameProject && matchingTimeInterval;
    }

    public static void main(String[] args) {
        File file = new File("./src/resources/employeesData.txt");
        String[][] employeeData = readInputFile.readFile(file);
        boolean workedTogether = sameTimeOnProject(employeeData[1],employeeData[2]);
        if (workedTogether){

            long days = dateTimeManipulation.daysTogether(employeeData[1],employeeData[2]);
            System.out.printf("Worked together for : %d days",days);
        }
    }
}
