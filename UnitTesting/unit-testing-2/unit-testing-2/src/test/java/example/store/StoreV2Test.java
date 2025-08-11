package example.store;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StoreV2Test {

    @Test
    void givenQuantityEquals0_whenBuy_thenFailed() {
        Product product = new Product();
        product.setQuantity(0);
        Customer customer = new Customer();
        AccountManager accountManager = new AccountManagerImpl();
        StoreImpl store = new StoreImpl(accountManager);
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }

    @Test
    void givenQuantityNotEquals0_whenBuy_thenSucceed() {
        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        Store store = new StoreImpl(accountManager);
        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
    }

    @Test
    void givenQuantityNotEquals0_whenBuy_thenFailed() {
        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("fail");
        Store store = new StoreImpl(accountManager);
        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();

        // Assert
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }


}
