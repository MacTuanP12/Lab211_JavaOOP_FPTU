import java.util.Scanner;

/**
 * Validate class for input validation
 */
public class Validate {
    private final static Scanner in = new Scanner(System.in);
    private final static String VALID_USERNAME = "^\\S{5,}$";
    private final static String VALID_PASSWORD = "^\\S{6,}$";

    /**
     * Check user input number limit
     */
    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Check user input string
     */
    public static String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    /**
     * Allow user input username
     */
    public static String checkInputUsername() {
        //loop until user input correct
        while (true) {
            System.out.print("Enter Username: ");
            String result = in.nextLine().trim();
            if (result.matches(VALID_USERNAME)) {
                return result;
            }
            System.err.println("You must enter least at 5 character, and no space!");
        }
    }

    /**
     * Allow user input password
     */
    public static String checkInputPassword() {
        //loop until user input correct
        while (true) {
            System.out.print("Enter Password: ");
            String result = in.nextLine().trim();
            if (result.matches(VALID_PASSWORD)) {
                return result;
            }
            System.err.println("You must enter least at 6 character, and no space!");
        }
    }
}

