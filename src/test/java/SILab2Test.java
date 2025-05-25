import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Item> createList(Item... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }
    @Test
    void everyStatementTest() {
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, ""));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        List<Item> allItems1 = createList(new Item("name", 10,  301,  0.1), new Item("name", 10, 301, 0), new Item(null, 1, 1, 0.1));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems1, ""));
        assertTrue(ex.getMessage().contains("Invalid item!"));

        List<Item> allItems2 = createList(new Item("name", 10,  301,  0.1), new Item("name", 10, 301, 0));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems2, null));
        assertTrue(ex.getMessage().contains("Invalid card number!"));

        List<Item> allItems3 = createList(new Item("name", 10,  301,  0.1), new Item("name", 10, 301, 0));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems3, "123044423423213d"));
        assertTrue(ex.getMessage().contains("Invalid character in card number!"));

        List<Item> allItems4 = createList(new Item("name", 10,  301,  0.1), new Item("name", 10, 301, 0));
        assertEquals(5659, SILab2.checkCart(allItems4, "1230444234232135"));
    }

    @Test
    void multipleConditionsTest() {
        //if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
        //T || X || X
        //F || T || X
        //F || F || T
        //F || F || F

        List<Item> allItems1 = createList(new Item("name", 1,  301,  0));
        assertEquals(271, SILab2.checkCart(allItems1, "1230444234232135"));

        List<Item> allItems2 = createList(new Item("name", 1,  300,  0.1));
        assertEquals(240, SILab2.checkCart(allItems2, "1230444234232135"));

        List<Item> allItems3 = createList(new Item("name", 11,  300,  0));
        assertEquals(3270, SILab2.checkCart(allItems3, "1230444234232135"));

        List<Item> allItems4 = createList(new Item("name", 1,  300,  0));
        assertEquals(300, SILab2.checkCart(allItems4, "1230444234232135"));

    }
}