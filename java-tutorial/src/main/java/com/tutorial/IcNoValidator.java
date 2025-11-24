package com.tutorial;

public class IcNoValidator {
    public static void main(String[] args) {
        //Invalid month & day
        String icNo = "123476-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Invalid day
        icNo = "121276-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Invalid month
        icNo = "123416-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Leap year invalid day
        icNo = "000230-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Leap year valid day
        icNo = "000229-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Non leap year invalid day
        icNo = "250229-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Non leap year valid day
        icNo = "250228-12-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Invalid number
        icNo = "123476-1B-A234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Invalid format
        icNo = "1234-08-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
        //Valid IC
        icNo = "980131-05-1234";
        System.out.println(String.format("%s is valid IC number? %s", icNo, isValidICNo(icNo)));
    
    }

    static boolean isValidICNo(String icNo) {
        return icNo.matches("\\d{6}-\\d{2}-\\d{4}") && isValidBirthDate(icNo);
    }

    static boolean isValidBirthDate(String icNo) {
        String yearLastTwoDigits = icNo.substring(0, 2);
        int yearLastTwo = Integer.parseInt(yearLastTwoDigits);
        int fullYear = yearLastTwo > 30 ? 1900 + yearLastTwo : 2000 + yearLastTwo;
        String monthDigits = icNo.substring(2, 4);
        int month = Integer.parseInt(monthDigits);
        String dayDigits = icNo.substring(4, 6);
        int day = Integer.parseInt(dayDigits);
        // System.out.println("Full year="+fullYear+" / by 4? "+(fullYear % 4 == 0)+" !/ by 100? "+(fullYear % 100 != 0)+" / by 400? "+(fullYear % 400 == 0));
        boolean isLeapYear = fullYear % 4 == 0 && (fullYear % 100 != 0 || (fullYear % 100 == 0 && fullYear % 400 == 0) );
        
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 
            && month == 10 || month == 12) {
            if(day>31) return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if(day>30) return false;
        } else if (month == 2) {
            if(isLeapYear) {
                if(day>29) return false;
            } else {
                if(day>28) return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
