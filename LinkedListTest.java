import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void test() {
		LinkedList<Integer> list = new LinkedList<>();
        list.addToFront(5);
        list.addToFront(4);
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        assertEquals("list: 2 1 3 4 5", list.moveBack(1));
        assertEquals("list: 2 3 1 4 5", list.moveBack(2));
        assertEquals("list: 1 2 3 4 5", list.moveBack(5));
        assertEquals("", list.moveBack(6));
	}

}
