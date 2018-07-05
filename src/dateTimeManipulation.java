import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class dateTimeManipulation {

    /**
     *  Parses a String to a Date type
     * @param input input the string
     * @return returns a Date type in "yyyy-MM-dd" format
     */
    public static Date parseDate(String input){
        DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date();
        try {
            if (input.equals("NULL")){
                String now = sdf.format(parsedDate.getTime());
                parsedDate = sdf.parse(now);
            } else {
                parsedDate = sdf.parse(input);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    /**
     * Returns how many days the employees worked together
     * @param emp1 String array of the first employee
     * @param emp2 String array of the second employee
     * @return returns the days in long type
     */
    public static long daysTogether(String[] emp1, String[] emp2){
        long days;
        Date emp1Start = dateTimeManipulation.parseDate(emp1[2]);
        Date emp1Finish = dateTimeManipulation.parseDate(emp1[3]);
        Date emp2Start = dateTimeManipulation.parseDate(emp2[2]);
        Date emp2Finish = dateTimeManipulation.parseDate(emp2[3]);
        long fistToFinish = Math.min(emp1Finish.getTime(),emp2Finish.getTime());
        long lastToStart =  Math.max(emp1Start.getTime(),emp2Start.getTime());
        days = TimeUnit.MILLISECONDS.toDays(fistToFinish - lastToStart);
        System.out.println(fistToFinish);
        System.out.println(lastToStart);
        System.out.println(days);
        return days;
    }
}
