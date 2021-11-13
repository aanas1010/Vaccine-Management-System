package drivers;

import Constants.BookingConstants;
import Constants.ExceptionConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This is the class for static Data Validation methods and
 * getting user inputs from the command line
 */

public class DataValidation {
    protected enum ParameterTypes {
        NON_NEGATIVE_INT,
        POSITIVE_INT,
        NON_PAST_DATE,
        FREE_TEXT,
        NON_PAST_DATETIME,
        COMMAND_CORE,
        COMMAND_BOOKABLE
    }

    protected enum CoreCommands {
        ADD_BATCH,
        SET_EMPLOYEES,
        ADD_TIME_PERIOD,
        REMOVE_TIME_PERIOD,
        ADD_TIME_PERIODS,
        QUIT;

        // Return whether s is a valid element of CoreCommands
        public static boolean contains(String s) {
            for (CoreCommands c : CoreCommands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;

        }
    }

    protected enum BookableCommands {
        BOOK_APPOINTMENT,
        CANCEL_APPOINTMENT,
        VIEW_APPOINTMENT,
        LOG_APPOINTMENT,
        LOG_WALK_IN,
        LOG_BY_DATETIME,
        LOG_BY_DATE;

        // Return whether s is a valid element of BookableCommands
        public static boolean contains(String s) {
            for (BookableCommands c : BookableCommands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }


    // Return the value given by the command line if it is a valid value in the list of parameter types
    protected static Enum<?> getCommand(Scanner in, String prompt, ArrayList<Enum<?>> types) {
        while(true) {
            System.out.println(prompt);
            System.out.print(BookingConstants.COMMAND_LINE_PROMPT);

            String input = in.nextLine();

            for(Enum<?> type : types) {
                if(type.toString().equals(input)) {
                    return type;
                }
            }

            System.out.println("That command is invalid. Please try again");

        }
    }

    // Helper function for input and output to command line
    protected static Object getValue(Scanner in, String prompt, ParameterTypes type) {
        while(true) {
            System.out.println(prompt);
            System.out.print(BookingConstants.COMMAND_LINE_PROMPT);

            String input = in.nextLine();

            boolean hasValidValue;
            Object formattedValue;
            switch(type) {
                case NON_NEGATIVE_INT:
                    formattedValue = tryParseInt(input);
                    hasValidValue = (Integer) formattedValue != -1;
                    break;
                case POSITIVE_INT:
                    formattedValue = tryParseInt(input);
                    hasValidValue = (Integer) formattedValue > 0;
                    break;
                case NON_PAST_DATE:
                    formattedValue = isNonPastDate(input);
                    hasValidValue = formattedValue != null;
                    break;
                case NON_PAST_DATETIME:
                    formattedValue = isNonPastDateTime(input);
                    hasValidValue = formattedValue != null;
                    break;
                case COMMAND_CORE:
                    formattedValue = DataValidation.CoreCommands.valueOf(input);
                    hasValidValue = CoreCommands.contains(input);
                    break;
                case COMMAND_BOOKABLE:
                    formattedValue = DataValidation.BookableCommands.valueOf(input);
                    hasValidValue = BookableCommands.contains(input);
                    break;
                default:
                    formattedValue = input;
                    hasValidValue = true;
                    break;
            }

            if(hasValidValue) {
                return formattedValue;
            }else {
                System.out.println(ExceptionConstants.INVALID_PARAMETER);
            }
        }
    }

    // Return the parsed int if it is a valid non-negative integer
    private static int tryParseInt(String value) {
        try{
            return Math.max(parseInt(value), -1);
        }catch(Exception ex){
            return -1;
        }
    }

    // Return the parsed date if it is a valid non-past date
    private static LocalDate isNonPastDate(String value) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate dateObj = LocalDate.parse(value, formatter);
            if(dateObj.isAfter(LocalDate.now())) {
                return dateObj;
            } else {
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }

    // Return the parsed datetime if it is a valid non-past datetime
    private static LocalDateTime isNonPastDateTime(String value) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
            LocalDateTime dateObj = LocalDateTime.parse(value, formatter);
            if(dateObj.isAfter(LocalDateTime.now())) {
                return dateObj;
            } else {
                return null;
            }
        }catch(Exception ex){
            return null;
        }
    }

}
