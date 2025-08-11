package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSucceed() {
        // Arrange
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);

        // Act
        String result = am.withdraw(c, 500);

        // Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(500, c.getBalance());
    }

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenFailed_InsufficientBalance_CreditNotAllowed() {
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);
        c.setCreditAllowed(false);
        String result = am.withdraw(c, 5000);
        Assertions.assertEquals("insufficient account balance", result);

    }

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSucceed_InsufficientBalance_CreditAllowed() {
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);
        c.setCreditAllowed(true);
        String result = am.withdraw(c, 2000);
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-1000, c.getBalance());

    }

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenFailed_CreditAllowed_MaxCreditExceeded_NotVIP() {
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);
        c.setCreditAllowed(true);
        c.setVip(false);
        String result = am.withdraw(c, 5000);
        Assertions.assertEquals("maximum credit exceeded", result);
/*
Expected :maximum credit exceeded
Actual   :success
Bug in the code
*/
    }

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSuccessed_CreditAllowed_MaxCreditExceeded_VIP() {
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);
        c.setCreditAllowed(true);
        c.setVip(true);
        String result = am.withdraw(c, 5000);
        Assertions.assertEquals("success", result);
    }
}
