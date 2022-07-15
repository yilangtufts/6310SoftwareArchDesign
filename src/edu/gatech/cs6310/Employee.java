package edu.gatech.cs6310;

public class Employee {
    private String taxID;
    private Integer numberOfMonths;
    private Double Salary;

    public Employee(String taxID, Integer numberOfMonths, Double salary) {
        this.taxID = taxID;
        this.numberOfMonths = numberOfMonths;
        Salary = salary;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }
}
