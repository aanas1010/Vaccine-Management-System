package drivers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This is the class for static Data Validation methods and
 * getting user inputs from the command line
 */

public class DataValidation {
    protected enum ParameterTypes {
        NON_NEGATIVE_INT,
        NON_PAST_DATE,
        COMMAND,
        FREE_TEXT
    }
    protected enum Commands {
        ADD_BATCH,
        QUIT;

        // Return whether s is a valid element of Commands
        public static boolean contains(String s) {
            for (Commands c : Commands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    // Helper function for input and output to command line
    protected static Object getValue(Scanner in, String prompt, ParameterTypes type) {
        while(true) {
            System.out.println(prompt);
            System.out.print(CommandLine.commandLinePrompt);

            String input = in.nextLine();

            boolean hasValidValue;
            Object formattedValue;
            switch(type) {
                case NON_NEGATIVE_INT:
                    formattedValue = tryParseInt(input);
                    hasValidValue = (Integer) formattedValue != -1;
                    break;
                case NON_PAST_DATE:
                    formattedValue = isNonPastDate(input);
                    hasValidValue = formattedValue != null;
                    break;
                case COMMAND:
                    formattedValue = input;
                    hasValidValue = Commands.contains(input);
                    break;
                default:
                    formattedValue = input;
                    hasValidValue = true;
                    break;
            }

            if(hasValidValue) {
                return formattedValue;
            }else {
                System.out.println("That value is invalid. Please try again");
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

}
