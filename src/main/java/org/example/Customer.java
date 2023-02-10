package org.example;

public class Customer {
    public String name;
    public double totalLoan;
    public double annualInterestRate;
    public int totalYears;
    private static final int MONTHS_IN_A_YEAR = 12;

    public Customer(String name, double totalLoan, double interestRate, int totalYears) {
        this.name = name;
        this.totalLoan = totalLoan;
        this.annualInterestRate = interestRate;
        this.totalYears = totalYears;
    }

    public double calculateMonthlyPayment() {
        double numberOfPayments = totalYears * MONTHS_IN_A_YEAR;
        double monthlyInterestRate = annualInterestRate / 100 / MONTHS_IN_A_YEAR;
        double pow = 1;
        for (int i = 0; i < numberOfPayments; i++) {
            pow *= (1 + monthlyInterestRate);
        }
        return totalLoan * (monthlyInterestRate * pow / (pow - 1));
    }
}
