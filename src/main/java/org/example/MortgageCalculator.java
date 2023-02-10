package org.example;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class MortgageCalculator {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        Scanner input = null;
        try {
            input = new Scanner(new File("prospects.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        input.nextLine();
        int customerCount = 0;

        while (input.hasNextLine()) {
            // Check if the line is empty
            String line = input.nextLine().trim();
            if (line.isEmpty()) continue;

            try {
                Customer customer = validateCustomer(line);
                System.out.printf("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month\n",
                        customerCount + 1,
                        customer.name,
                        customer.totalLoan,
                        customer.totalYears,
                        customer.calculateMonthlyPayment());

            } catch (IllegalArgumentException e) {
                //System.out.println(e.getMessage());
                continue;
            }
            customerCount++;
        }
    }

    static Customer validateCustomer(String line) {
        String[] values = line.split(",");
        // Make sure that the input length is valid

        if (values.length < 4) {
            throw new IllegalArgumentException("Invalid input: " + line);
        }

        String customer;
        int offset = 0;
        if (values.length > 4) {
            offset = 1;
            customer = values[0] + " " + values[1];
        } else {
            customer = values[0];
        }
        customer = customer.replaceAll("[\"]","");

        try {
            double Principal = Double.parseDouble(values[offset + 1]);
            double interestRate = Double.parseDouble(values[offset + 2]);
            int years = Integer.parseInt(values[offset + 3]);
            return new Customer(customer,Principal,interestRate,years);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input: " + e);
        }
    }
}