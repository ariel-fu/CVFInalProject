import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
  private BankAccount test;

  @BeforeEach
  void setUp() throws Exception {
    test = new BankAccount(1, 100.0);
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testBalance() {
    assertTrue(test.balance() == 100.0);
  }

  @Test
  void testDepositSimple() {
    test.deposit(0.0);
    assertTrue(test.balance() == 100.0);

  }

  @Test
  void testDepositComplicated() {
    test.deposit(0.0);
    assertTrue(test.balance() == 100.0);
    test.deposit(-10.0);
    assertTrue(test.balance() == 90.0);
    test.deposit(19.3);
    assertTrue(test.balance() == 109.3);
    test.deposit(-110.3);
    assertTrue(test.balance() == -1.0);
  }

  @Test
  void testWithdraw() {
    test.withdraw(0.0);
    assertTrue(test.balance() == 100.0);
  }

  @Test
  void testWithdraw2() {
    test.withdraw(-1.0);
    assertTrue(test.balance() == 101.0);
    test.withdraw(1.0);
    assertTrue(test.balance() == 100.0);

  }
}
