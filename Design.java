import java.util.Scanner;

/**
 * @author Wen Bin
 * @version 2025/03/28
 * <p>
 * Calculate the day number of the year
 * <p>
 */
public class Design {
    public static int isLeap(int year){
        // leap years occur every for every year that is divisible by 4
        if(year%4==0){
            return 1;//one more day in the year if it is a leap year (february changes from 28 to 29)
        }
        else{
            return 0;//normal year (365days in a year)
        }
    }

    public static boolean checkDate(String date){
        String[] dateArray = date.split("/");//check main() for explanation
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        if(year<=0){
            System.out.println("Year must be greater than 0");
            return false;
        }
        if(month>12){
            System.out.println("Month cannot be grater than 12");
            return false;
        }
        if(month<=0){
            System.out.println("Month cannot be negative or zero");
            return false;
        }
        if(day<0){
            System.out.println("Day cannot be negative");
            return false;
        }
        if(month==1&&day>31){
            System.out.println("Day cannot be greater than 31 in January");
            return false;
        }
        if(month==2&&day>28+isLeap(year)){
            if(isLeap(year)==0){
                System.out.println("Day cannot be greater than 28 in February (Not Leap Year)");
            }
            else{
                System.out.println("Day cannot be greater than 29 in February (Leap Year)");
            }
            return false;
        }
        if(month==3&&day>31){
            System.out.println("Day cannot be greater than 31 in March");
            return false;
        }
        if(month==4&&day>30){
            System.out.println("Day cannot be greater than 30 in April");
            return false;
        }
        if(month==5&&day>31){
            System.out.println("Day cannot be greater than 31 in May");
            return false;
        }
        if(month==6&&day>30){
            System.out.println("Day cannot be greater than 30 in June");
            return false;
        }
        if(month==7&&day>31){
            System.out.println("Day cannot be greater than 31 in July");
            return false;
        }
        if(month==8&&day>31){
            System.out.println("Day cannot be greater than 31 in August");
            return false;
        }
        if(month==9&&day>30){
            System.out.println("Day cannot be greater than 30 in September");
            return false;
        }
        if(month==10&&day>31){
            System.out.println("Day cannot be greater than 31 in October");
            return false;
        }
        if(month==11&&day>30){
            System.out.println("Day cannot be greater than 30 in November");
            return false;
        }
        if(month==12&&day>31){
            System.out.println("Day cannot be greater than 31 in December");
            return false;
        }
        return true;
    }

    public static boolean isValid(String date){
        if(date.length()!=10){
            System.out.println("Length of string should be 10");
            return false;
        }
        char[] c = date.toCharArray();
        if(c[4]!='/'||c[7]!='/'){
            System.out.println("'/' are missing in position 4 or 7 (0 index)");
            return false;
        }
        for (int i = 0; i < c.length; i++) {
            if(i==4 || i==7){
                continue;
            }
            else if(!Character.isDigit(c[i])){
                System.out.println("Input is not all digits (except for the 2 /'s)");
                return false;
            }
        }

        return checkDate(date);
    }

    public static int getDays(int year, int month, int day){
        int n=0;//the day number of the year starts at 0
        if(month==1){
            n = day;//No months before january so the day number of year is just day
        }
        else if(month==2){
            //January has 31 days
            n = day + 31;//total days of previous months + day
        }
        else if(month==3){
            //February has 28 days (or 29 if it is a leap year)
            n = day + 31 + 28 + isLeap(year);//need to check id february has 28 or 29 days
        }
        else if(month==4){
            //March has 31 days
            n = day + 31 + 28 + 31 + isLeap(year);//from the previous onwards we need to check if it is a leap year and add one day accordingly
        }
        else if(month==5){
            //April has 30 days
            n = day + 31 + 28 + 31 + 30 + isLeap(year);
        }
        else if(month==6){
            //May has 31 days
            n = day + 31 + 28 + 31 + 30 + 31+ isLeap(year);
        }
        else if(month==7){
            //June has 30 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30+ isLeap(year);
        }
        else if(month==8){
            //July has 31 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30 + 31+ isLeap(year);
        }
        else if(month==9){
            //August has 31 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31+ isLeap(year);
        }
        else if(month==10){
            //September has 30 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30+ isLeap(year);
        }
        else if(month==11){
            //October has 31 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31+ isLeap(year);
        }
        else if(month==12){
            //November has 30 days
            n = day + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30+ isLeap(year);
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******Welcome to the Get Day of the Year Programme******");
        System.out.println("Fact: A year is a Leap Year only if it is divisible by 4");
        System.out.println("*********************************************************");
        boolean valid = false;
        String date = "";
        while (!valid) {//if input not valid, ask user again until input is valid
            System.out.print("Input date as YYYY/MM/DD: ");
            date = scanner.nextLine();//getting the input
            valid = isValid(date);//check if it is a valid input
        }
        String[] dateArray = date.split("/");//splitting input into YYYY/MM/DD
        int year = Integer.parseInt(dateArray[0]);//get the year parsed into an integer
        int month = Integer.parseInt(dateArray[1]);//get the month parsed into an integer
        int day = Integer.parseInt(dateArray[2]);//get the day parsed into an integer

        scanner.close();//good practice to close scanner
        System.out.println("*********************************************************");
        System.out.println("The day number of the year is "+getDays(year, month, day));//printing the day number of the year
        System.out.println("*********************************************************");

    }
}
