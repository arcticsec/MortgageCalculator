import org.example.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageCalculatorTest {
    @Test
    public void testMonthlyPayment() {
        double totalLoan = 100000;
        double annualInterestRate = 5;
        int years = 30;
        double expectedAmount = 536.82;

        Customer customer = new Customer("Martin Björklund", totalLoan, annualInterestRate, years);
        assertEquals(expectedAmount, customer.calculateMonthlyPayment(), 0.01);
    }
}
