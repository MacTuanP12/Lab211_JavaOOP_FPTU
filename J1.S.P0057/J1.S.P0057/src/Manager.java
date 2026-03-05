import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Manager {
    private static final String FILE_PATH = "user.dat";
    private static List<Account> accounts = new ArrayList<>();

    public static int menu() {
        System.out.println("====== USER MANAGEMENT SYSTEM ======");
        System.out.println("1. Create a new account");
        System.out.println("2. Login system");
        System.out.println("3. Exit");
        System.out.print("> Choose: ");
        int choice = Validate.checkInputIntLimit(1, 3);
        return choice;
    }

    public static void createNewAccount() {
        loadAccounts();

        //loop until create successfully
        while (true) {
            String username = Validate.checkInputUsername();

            //check username exist or not
            if (checkUsernameExist(username)) {
                System.err.println("Username already exists!");
                continue;
            }

            String password = Validate.checkInputPassword();

            //add account
            Account newAccount = new Account(username, password);
            accounts.add(newAccount);
            saveAccounts();

            System.out.println("Account created successfully!");
            break;
        }
    }

    /**
     * Login system
     */
    public static void loginSystem() {
        loadAccounts();

        //loop until login successfully
        while (true) {
            String username = Validate.checkInputUsername();
            String password = Validate.checkInputPassword();

            //find account
            Account account = findAccount(username, password);
            if (account != null) {
                System.out.println("Login successful!");
                break;
            } else {
                System.err.println("Invalid user name or password");
            }
        }
    }

    /**
     * Check if username exists
     */
    private static boolean checkUsernameExist(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find account by username and password
     */
    private static Account findAccount(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)
                && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Load accounts from file
     */
    private static void loadAccounts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            accounts = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            accounts = new ArrayList<>();
        }
    }

    /**
     * Save accounts to file
     */
    private static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }
}

