package Controller;

import Model.Doctor;
import Model.DoctorHash;
import View.Menu;
import View.Validation;

import java.util.HashMap;

public class DoctorManager {
    private final DoctorHash doctorHash;
    private Menu mainMenu;

    public DoctorManager() {
        doctorHash = new DoctorHash();
        setupMenu();
    }

    private void setupMenu() {
        mainMenu = new Menu("Doctor Management");
        mainMenu.addOption("Add Doctor");
        mainMenu.addOption("Update Doctor");
        mainMenu.addOption("Delete Doctor");
        mainMenu.addOption("Search Doctor");
        mainMenu.addOption("Exit");
    }

    public void run() {
        while (true) {
            mainMenu.display();
            int choice = mainMenu.getChoice();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    updateDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    searchDoctor();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    return;
            }
        }
    }

    private void addDoctor() {
        System.out.println("\n--------- Add Doctor ----------");
        try {
            String code = Validation.getString("Enter Code: ");
            String name = Validation.getString("Enter Name: ");
            String specialization = Validation.getString("Enter Specialization: ");
            int availability = Validation.getPositiveInt("Enter Availability: ");

            Doctor doctor = new Doctor(code, name, specialization, availability);
            doctorHash.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateDoctor() {
        System.out.println("\n--------- Update Doctor -------");
        try {
            String code = Validation.getString("Enter Code: ");

            if (!doctorHash.isDoctorExist(code)) {
                System.out.println("Doctor code does not exist");
                return;
            }

            Doctor existingDoctor = doctorHash.getDoctorByCode(code);
            System.out.println("Current information: " + existingDoctor);
            System.out.println("(Leave blank to keep current value)");

            String name = Validation.getStringAllowEmpty("Enter Name: ");
            String specialization = Validation.getStringAllowEmpty("Enter Specialization: ");
            Integer availability = Validation.getPositiveIntAllowEmpty("Enter Availability: ");

            // Keep old values if input is blank
            if (name.isEmpty()) {
                name = existingDoctor.getName();
            }
            if (specialization.isEmpty()) {
                specialization = existingDoctor.getSpecialization();
            }
            if (availability == null) {
                availability = existingDoctor.getAvailability();
            }

            Doctor updatedDoctor = new Doctor(code, name, specialization, availability);
            doctorHash.updateDoctor(updatedDoctor);
            System.out.println("Doctor updated successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteDoctor() {
        System.out.println("\n--------- Delete Doctor -------");
        try {
            String code = Validation.getString("Enter Code: ");

            if (!doctorHash.isDoctorExist(code)) {
                System.out.println("Doctor code does not exist");
                return;
            }

            Doctor doctor = doctorHash.getDoctorByCode(code);
            System.out.println("Doctor to delete: " + doctor);

            if (Validation.confirm("Are you sure you want to delete this doctor?")) {
                doctorHash.deleteDoctor(doctor);
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchDoctor() {
        System.out.println("\n---------- Search Doctor --------");
        try {
            String searchText = Validation.getString("Enter text: ");
            HashMap<String, Doctor> results = doctorHash.searchDoctor(searchText);

            if (results.isEmpty()) {
                System.out.println("No doctors found matching: " + searchText);
            } else {
                System.out.println("---------- Result ------------");
                System.out.printf("%-10s %-20s %-20s %s\n", "Code", "Name", "Specialization", "Availability");
                for (Doctor doctor : results.values()) {
                    System.out.println(doctor);
                }
                System.out.println("Found " + results.size() + " doctor(s).");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

