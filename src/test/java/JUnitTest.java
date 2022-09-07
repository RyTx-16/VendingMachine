import static org.junit.jupiter.api.Assertions.*;

import VendingMachine.dto.Item;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 00:02
 */

public class JUnitTest {

    Item a = new Item("Pepsi", new BigDecimal(1.50), 15);

    @Test
    public void testOne() {
        int x = a.getInInventory();
        assertEquals(5, x, "Test Fail");
    }

    @Test
    public void testTwo() {
        int x = a.getInInventory();
        assertEquals(15, x, "Test Pass");
    }

}
