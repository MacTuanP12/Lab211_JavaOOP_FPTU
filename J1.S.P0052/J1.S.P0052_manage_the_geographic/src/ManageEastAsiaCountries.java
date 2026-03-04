import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ManageEastAsiaCountries {
    private ArrayList<EastAsiaCountries> countries;
    private Scanner scanner;

    public ManageEastAsiaCountries() {
        countries = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Function to add country information
    public void addCountryInformation(EastAsiaCountries country) throws Exception {
        if (country == null) {
            throw new Exception("Country object cannot be null");
        }
        if (country.getTotalArea() <= 0) {
            throw new Exception("Total area must be greater than 0");
        }
        countries.add(country);
    }

    // Function to get recently entered information
    public EastAsiaCountries getRecentlyEnteredInformation() throws Exception {
        if (countries.isEmpty()) {
            throw new Exception("No country information available");
        }
        return countries.get(countries.size() - 1);
    }

    // Function to search information by name
    public EastAsiaCountries[] searchInformationByName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Country name cannot be empty");
        }

        ArrayList<EastAsiaCountries> results = new ArrayList<>();
        for (EastAsiaCountries country : countries) {
            if (country.getCountryName().toLowerCase().contains(name.toLowerCase())) {
                results.add(country);
            }
        }

        return results.toArray(new EastAsiaCountries[0]);
    }

    // Function to sort information by ascending order
    public EastAsiaCountries[] sortInformationByAscendingOrder() throws Exception {
        if (countries.isEmpty()) {
            throw new Exception("No country information available to sort");
        }

        ArrayList<EastAsiaCountries> sortedList = new ArrayList<>(countries);
        Collections.sort(sortedList, new Comparator<EastAsiaCountries>() {
            @Override
            public int compare(EastAsiaCountries c1, EastAsiaCountries c2) {
                return c1.getCountryName().compareToIgnoreCase(c2.getCountryName());
            }
        });

        return sortedList.toArray(new EastAsiaCountries[0]);
    }

    // Function to display menu
    public void displayMenu() {
        System.out.println("                                                   MENU");
        System.out.println("=========================================================================");
        System.out.println("1. Input the information of 11 countries in East Asia");
        System.out.println("2. Display the information of country you've just input");
        System.out.println("3. Search the information of country by user-entered name");
        System.out.println("4. Display the information of countries sorted name in ascending order");
        System.out.println("5. Exit");
        System.out.println("=========================================================================");
    }

    // Function to input country information (one at a time)
    public void inputCountryInformation() {
        System.out.println("\nEnter information for a country in East Asia:");

        String code = inputString("Enter code of country: ");
        String name = inputString("Enter name of country: ");
        float area = inputFloat("Enter total Area: ");
        String terrain = inputString("Enter terrain of country: ");

        try {
            EastAsiaCountries country = new EastAsiaCountries(code, name, area, terrain);
            addCountryInformation(country);
            System.out.println("Country added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper method to input string
    private String inputString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    // Helper method to input float with validation
    private float inputFloat(String prompt) {
        float value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                value = Float.parseFloat(input);

                if (value <= 0) {
                    System.out.println("Total area must be greater than 0. Please try again.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return value;
    }

    // Function to display recently entered country
    public void displayRecentlyEntered() {
        try {
            EastAsiaCountries country = getRecentlyEnteredInformation();
            System.out.println("\n" + String.format("%-15s%-25s%-15s%-15s", "ID", "Name", "Total Area", "Terrain"));
            country.display();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Function to search by name
    public void searchByName() {
        String name = inputString("\nEnter the name you want to search for: ");

        try {
            EastAsiaCountries[] results = searchInformationByName(name);

            if (results.length == 0) {
                System.out.println("No countries found with the name: " + name);
            } else {
                System.out.println("\n" + String.format("%-15s%-25s%-15s%-15s", "ID", "Name", "Total Area", "Terrain"));
                for (EastAsiaCountries country : results) {
                    country.display();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Function to display sorted countries
    public void displaySortedCountries() {
        try {
            EastAsiaCountries[] sortedCountries = sortInformationByAscendingOrder();
            System.out.println("\n" + String.format("%-15s%-25s%-15s%-15s", "ID", "Name", "Total Area", "Terrain"));
            for (EastAsiaCountries country : sortedCountries) {
                country.display();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Main run method
    public void run() {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        inputCountryInformation();
                        break;
                    case 2:
                        displayRecentlyEntered();
                        break;
                    case 3:
                        searchByName();
                        break;
                    case 4:
                        displaySortedCountries();
                        break;
                    case 5:
                        System.out.println("Exiting program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

                if (choice != 5) {
                    System.out.println(); // Add blank line for better readability
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0; // Continue the loop
            }
        } while (choice != 5);

        scanner.close();
    }
}

