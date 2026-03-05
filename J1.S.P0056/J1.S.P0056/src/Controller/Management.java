package Controller;

import Model.SalaryHistory;
import Model.SalaryStatus;
import Model.Worker;

import java.time.LocalDate;
import java.util.*;

public class Management {
    private Map<String, Worker> workerDatabase;
    private List<SalaryHistory> salaryHistoryList;

    public Management() {
        workerDatabase = new HashMap<>();
        salaryHistoryList = new ArrayList<>();
        initializeSampleData();
    }

    /**
     * Initialize sample data for testing
     */
    private void initializeSampleData() {
        try {
            // Add sample workers
            Worker worker1 = new Worker("W 1", "Nghia", 20, 1100, "Hanoi");
            Worker worker2 = new Worker("W 2", "Lien", 20, 1300, "Ho Chi Minh");
            Worker worker3 = new Worker("W 3", "Tuan", 25, 1500, "Da Nang");

            workerDatabase.put(worker1.getCode(), worker1);
            workerDatabase.put(worker2.getCode(), worker2);
            workerDatabase.put(worker3.getCode(), worker3);

            // Sample salary history (simulating previous adjustments)
            // Worker Nghia - UP salary to 1100 on 23/06/2015
            SalaryHistory history1 = new SalaryHistory(
                "W 1", "Nghia", 20, 1100, SalaryStatus.UP,
                LocalDate.of(2015, 6, 23)
            );

            // Worker Nghia - UP salary to 1500 on 23/07/2015
            SalaryHistory history2 = new SalaryHistory(
                "W 1", "Nghia", 20, 1500, SalaryStatus.UP,
                LocalDate.of(2015, 7, 23)
            );

            // Worker Lien - DOWN salary to 1300 on 23/07/2015
            SalaryHistory history3 = new SalaryHistory(
                "W 3", "Lien", 20, 1300, SalaryStatus.DOWN,
                LocalDate.of(2015, 7, 23)
            );

            salaryHistoryList.add(history1);
            salaryHistoryList.add(history2);
            salaryHistoryList.add(history3);

        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
        }
    }


    public boolean addWorker(Worker worker) throws Exception {
        if (worker == null) {
            throw new Exception("Data does not exist");
        }

        if (worker.getCode() == null || worker.getCode().trim().isEmpty()) {
            throw new Exception("Worker code cannot be null or empty");
        }

        String code = worker.getCode().trim();

        if (workerDatabase.containsKey(code)) {
            throw new Exception("Worker code [" + code + "] is duplicate");
        }

        workerDatabase.put(code, worker);
        return true;
    }


    public boolean changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (code == null || code.trim().isEmpty()) {
            throw new Exception("Worker code cannot be null or empty");
        }

        code = code.trim();

        if (!workerDatabase.containsKey(code)) {
            throw new Exception("Worker code [" + code + "] does not exist");
        }

        if (amount <= 0) {
            throw new Exception("Amount must be greater than 0");
        }

        Worker worker = workerDatabase.get(code);
        double oldSalary = worker.getSalary();
        double newSalary;

        if (status == SalaryStatus.UP) {
            newSalary = oldSalary + amount;
        } else {
            newSalary = oldSalary - amount;
            if (newSalary < 0) {
                throw new Exception("Salary cannot be negative after decrease");
            }
        }

        worker.setSalary(newSalary);

        // Save salary history
        SalaryHistory history = new SalaryHistory(
            worker.getCode(),
            worker.getName(),
            worker.getAge(),
            newSalary,
            status,
            LocalDate.now()
        );
        salaryHistoryList.add(history);

        return true;
    }


    public List<SalaryHistory> getInfomationSalary() {
        List<SalaryHistory> sortedList = new ArrayList<>(salaryHistoryList);
        sortedList.sort(Comparator.comparing(SalaryHistory::getCode));
        return sortedList;
    }


    public boolean isWorkerExist(String code) {
        return workerDatabase.containsKey(code);
    }


    public Worker getWorkerByCode(String code) {
        return workerDatabase.get(code);
    }


    public Map<String, Worker> getAllWorkers() {
        return new HashMap<>(workerDatabase);
    }
}

