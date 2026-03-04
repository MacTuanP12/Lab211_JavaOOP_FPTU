package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryHistory {
    private String code;
    private String name;
    private int age;
    private double salary;
    private SalaryStatus status;
    private LocalDate date;

    public SalaryHistory(String code, String name, int age, double salary, SalaryStatus status, LocalDate date) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%-10s %-20s %-5d %-15.2f %-10s %s",
            code, name, age, salary, status, date.format(formatter));
    }
}

