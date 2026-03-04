package Controller;

import Model.SalaryHistory;
import Model.SalaryStatus;
import Model.Worker;
import View.Menu;
import View.Validation;

import java.util.List;

public class WorkerManager {
    private final Management management;
    private Menu mainMenu;

    public WorkerManager() {
        management = new Management();
        setupMenu();
    }

    private void setupMenu() {
        mainMenu = new Menu("======== Worker Management ========");
        mainMenu.addOption("Add worker");
        mainMenu.addOption("Up salary");
        mainMenu.addOption("Down salary");
        mainMenu.addOption("Display Information salary");
        mainMenu.addOption("Exit");
    }

    public void run() {
        while (true) {
            mainMenu.display();
            int choice = mainMenu.getChoice();

            switch (choice) {
                case 1:
                    addWorker();
                    break;
                case 2:
                    increaseSalary();
                    break;
                case 3:
                    decreaseSalary();
                    break;
                case 4:
                    displaySalaryInformation();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    return;
            }
        }
    }

    /**
     * Add a new worker to the system
     */
    private void addWorker() {
        System.out.println("\n--------- Add Worker ---------");
        try {
            String code = Validation.getString("Enter Code: ");
            String name = Validation.getString("Enter Name: ");
            int age = Validation.getInt("Enter Age: ", 18, 50);
            double salary = Validation.getPositiveDouble("Enter Salary: ");
            String workLocation = Validation.getString("Enter Work Location: ");

            Worker worker = new Worker(code, name, age, salary, workLocation);
            management.addWorker(worker);
            System.out.println("Add worker success!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Increase salary for a worker
     */
    private void increaseSalary() {
        System.out.println("\n--------- Up Salary ---------");
        try {
            String code = Validation.getString("Enter Code: ");

            if (!management.isWorkerExist(code)) {
                System.out.println("Worker code does not exist!");
                return;
            }

            double amount = Validation.getPositiveDouble("Enter Salary: ");

            management.changeSalary(SalaryStatus.UP, code, amount);
            System.out.println("Update success");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Decrease salary for a worker
     */
    private void decreaseSalary() {
        System.out.println("\n--------- Down Salary ---------");
        try {
            String code = Validation.getString("Enter Code: ");

            if (!management.isWorkerExist(code)) {
                System.out.println("Worker code does not exist!");
                return;
            }

            double amount = Validation.getPositiveDouble("Enter Salary: ");

            management.changeSalary(SalaryStatus.DOWN, code, amount);
            System.out.println("Update success");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Display salary adjustment history for all workers
     */
    private void displaySalaryInformation() {
        System.out.println("\n--------- Display Information Salary ---------");
        List<SalaryHistory> historyList = management.getInfomationSalary();

        if (historyList.isEmpty()) {
            System.out.println("No salary adjustment history found.");
            return;
        }

        System.out.println(String.format("%-10s %-20s %-5s %-15s %-10s %s",
            "Code", "Name", "Age", "Salary", "Status", "Date"));
        System.out.println("-".repeat(80));

        for (SalaryHistory history : historyList) {
            System.out.println(history);
        }
    }
}

