package Model;

import java.util.HashMap;

public class DoctorHash {
    private HashMap<String, Doctor> doctorDatabase;

    public DoctorHash() {
        doctorDatabase = new HashMap<>();
    }

    /**
     * Check if availability is valid (must be >= 0)
     */
    public boolean checkAvailability(int availability) throws Exception {
        if (availability < 0) {
            throw new Exception("Availability must be greater than or equal to 0");
        }
        return true;
    }

    /**
     * Add a new doctor to the database
     */
    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data does not exist");
        }
        if (doctor.getCode() == null || doctor.getCode().trim().isEmpty()) {
            throw new Exception("Doctor code cannot be null or empty");
        }
        if (doctorDatabase.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code [" + doctor.getCode() + "] is duplicate");
        }
        checkAvailability(doctor.getAvailability());
        doctorDatabase.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Update an existing doctor's information
     */
    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data doesn't exist");
        }
        if (!doctorDatabase.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }
        checkAvailability(doctor.getAvailability());
        doctorDatabase.put(doctor.getCode(), doctor);
        return true;
    }

    /**
     * Delete a doctor from the database
     */
    public boolean deleteDoctor(Doctor doctor) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data doesn't exist");
        }
        // Normalize code to lowercase for case-insensitive comparison
        String normalizedCode = doctor.getCode().toLowerCase();
        if (!doctorDatabase.containsKey(normalizedCode)) {
            throw new Exception("Doctor code doesn't exist");
        }
        doctorDatabase.remove(normalizedCode);
        return true;
    }

    /**
     * Search for doctors by input string (searches in code, name, specialization)
     */
    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }
        HashMap<String, Doctor> result = new HashMap<>();
        String searchText = input.toLowerCase().trim();

        for (Doctor doctor : doctorDatabase.values()) {
            if (doctor.getCode().toLowerCase().contains(searchText) ||
                doctor.getName().toLowerCase().contains(searchText) ||
                doctor.getSpecialization().toLowerCase().contains(searchText)) {
                result.put(doctor.getCode(), doctor);
            }
        }
        return result;
    }

    /**
     * Get a doctor by code (case-insensitive)
     */
    public Doctor getDoctorByCode(String code) {
        if (code == null) {
            return null;
        }
        // Normalize code to lowercase for case-insensitive comparison
        return doctorDatabase.get(code.toLowerCase());
    }

    /**
     * Check if doctor code exists
     */
    public boolean isDoctorExist(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        // Normalize code to lowercase for case-insensitive comparison
        return doctorDatabase.containsKey(code.toLowerCase());
    }

    /**
     * Get all doctors
     */
    public HashMap<String, Doctor> getAllDoctors() {
        return doctorDatabase;
    }
}

