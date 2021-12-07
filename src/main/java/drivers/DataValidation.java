package drivers;

import constants.BookingConstants;
import constants.ManagementSystemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This is the class for static Data Validation methods and
 * getting user inputs from the command line
 */

public class DataValidation {

    /**
     * Parameter types accepted by the command line
     */
    protected enum ParameterTypes {
        NON_NEGATIVE_INT,
        POSITIVE_INT,
        NON_PAST_DATE,
        FREE_TEXT,
        NON_PAST_DATETIME,
        COMMAND_CORE,
        COMMAND_BOOKABLE
    }


    /**
     * Types of commands which must all have a contains method
     */
    protected enum CoreCommands {
        ADD_BATCH,
        SET_EMPLOYEES,
        ADD_TIME_PERIOD,
        REMOVE_TIME_PERIOD,
        ADD_TIME_PERIODS,
        QUIT;

        /**
         * Check if the inputted command is indeed a core command
         *
         * @param s a command. This command must be one of the commands in the CoreCommands enum
         * @return whether s is a valid element of CoreCommands
         */
        public static boolean contains(String s) {
            for (CoreCommands c : CoreCommands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;

        }
    }

    /**
     * Commands that can only be inputted if the clinic is declared a bookable clinic
     */
    protected enum BookableCommands {
        BOOK_APPOINTMENT,
        CANCEL_APPOINTMENT,
        VIEW_APPOINTMENT,
        LOG_APPOINTMENT,
        LOG_WALK_IN,
        LOG_BY_DATETIME,
        LOG_BY_DATE;

        /**
         * Check if the inputted command is indeed a bookable command
         *
         * @param s a command. This command must be one of the commands in the BookableCommands enum
         * @return whether s is a valid element of BookableCommands
         */
        public static boolean contains(String s) {
            for (BookableCommands c : BookableCommands.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * Gets the command inputted by the user
     *
     * @param in Scans/parses the text that the user inputs
     * @param prompt The display of the possible inputs. While the program is running, the prompt displays
     *               the commands that may be entered
     * @param types The possible choices of commands the user may input
     * @return the input given by the command line
     */
    protected static Enum<?> getCommand(Scanner in, String prompt, List<Enum<?>> types) {
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

    /**
     *
     * Helper function for input and output to command line
     *
     * @param in Scans/parses the text that the user inputs
     * @param prompt The display of the possible inputs. While the program is running, the prompt displays
     *               the commands that may be entered
     * @param type the type of parameter that the user inputted
     * @return the value if it is a valid value in the list of parameter types
     */
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
                System.out.println(ManagementSystemException.INVALID_PARAMETER);
            }
        }
    }

    /** Try to parse an int
     *
     * @param value a string to be parsed
     * @return the parsed int or -1 if invalid
     */
    private static int tryParseInt(String value) {
        try{
            return Math.max(parseInt(value), -1);
        }catch(Exception ex){
            return -1;
        }
    }

    /** Try to parse a date
     *
     * @param value a string to be parsed
     * @return the parsed date or null if invalid
     */
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

    /** Try to parse a dateTime
     *
     * @param value a string to be parsed
     * @return the parsed dateTime or null if invalid
     */
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
