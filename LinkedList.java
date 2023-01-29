import java.util.*;

/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
    /** the first node of the list, or null if the list is empty */
    private LLNode<T> firstNode;

    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }

    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }

    /**
     * Changes the front node.
     * @param node  the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }

    /**
     * Return whether the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }

    /**
     * Add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }

    /**
     * Removes and returns the element at the front of the linked list
     * @return the element removed from the front of the linked list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T save = getFirstNode().getElement();
            setFirstNode(getFirstNode().getNext());
            return save;
        }
    }

    /**
     * Returns the length of the linked list
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }

    /**
     * Adds an element to the end of the linkd list
     * @param element the element to insert at the end
     */
    public void addToEnd(T element) {
        if (isEmpty())
            addToFront(element);
        else {
            LLNode<T> nodeptr = getFirstNode();
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<T>(element, null));
        }
    }

    public LinkedList<T> deleteFirstNode() {
        setFirstNode(getFirstNode().getNext());
        return this;
    }

    /**
     * Return an iterator for this list
     * @return the iterator for the list
     */
    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(getFirstNode());
    }

    /* Static methods and generics:
     *   Generic types only go with instance methods and fields
     *   If I want a generic here, I must declare it in the method header,
     *   before the return type
     */

    /* Generic types and wildcards:
     *    If we don't care what the generic type is because we don't use that type
     *     (other than calling Object methods on it)
     *    we can use a wildcard that means we don't care what the generic type is
     */

    /**
     * Prints the contents of a linked list to System.out.
     * @param list the linked list to print
     */
    public static void printList(LinkedList<?> list) {
        for (Object element : list) {
            System.out.print(element);
            System.out.print(" ");
        }
        System.out.println();
    }

    /*-------------------------------------------*/
    /* THE NEXT METHODS WILL BE COMPLETED IN LAB */
    /*-------------------------------------------*/

    /**
     * Returns a String representation of the list
     * @return a String representing the list
     */
    public String toString() {
        LLNode<T> node = this.getFirstNode();
        StringBuilder str = new StringBuilder();
        str.append("list:");
        while(node != null){
            str.append(" ");
            str.append(node.getElement());
            node = node.getNext();

        }
        return str.toString();
    }

    /**
     * Determines whether an element is stored in the list
     * @param element  the element to search for in the list
     * @return true if and only if the parameter element is in the list
     */
    public boolean contains(T element) {
        LLNode node = this.getFirstNode();
        while(node != null) {
            if(node.getElement() == element) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Deletes the first occurrance of an element in the list.
     * If the element is not in the list, the list is unchanged.
     * @param element  the element to remove
     */
    public void remove(T element) {
        LLNode<T> node = getFirstNode();
        if ( node == null)
            ;
        else if ((node.getElement() == element) && node.getNext() == null)
            this.setFirstNode(null);
        else if (node.getElement() == element)
            this.setFirstNode(node.getNext());
        else{
            while (node.getNext() != null){
                if (node.getNext().getElement() == element){
                    node.deleteNext();
                    break;
                }
                node = node.getNext();
            }
        }
    }
    public void swap(LLNode<T> n1, LLNode<T> n2)
    {
        LLNode<T> prevNode1 = null;
        LLNode<T> prevNode2 = null;
        LLNode<T> node1 = getFirstNode();
        LLNode<T> node2 = getFirstNode();

        // Checks if list is empty
        if (getFirstNode() == null) {
            return;
        }

        // If n1 and n2 are equal, then
        // list will remain the same
        if (n1.getElement().equals(n2.getElement()))
            return;

        // Search for node1
        while (node1 != null && node1.getElement() != n1.getElement()) {
            prevNode1 = node1;
            node1 = node1.getNext();
        }

        // Search for node2
        while (node2 != null && node2.getElement() != n2.getElement()) {
            prevNode2 = node2;
            node2 = node2.getNext();
        }

        if (node1 != null && node2 != null) {

            // If previous node to node1 is not null then,
            // it will point to node2
            if (prevNode1 != null)
                prevNode1.setNext(node2);
            else
                setFirstNode(node2);

            // If previous node to node2 is not null then,
            // it will point to node1
            if (prevNode2 != null)
                prevNode2.setNext(node1);
            else
                setFirstNode(node1);

            // Swaps the next nodes of node1 and node2
            LLNode<T> temp = node1.getNext();
            node1.setNext(node2.getNext());
            node2.setNext(temp);
        }
        else {
            System.out.println("Swapping is not possible");
        }
    }
    public LinkedList<T> moveBack(int n) {
        if(n < this.length()) {
            LLNode<T> first = getFirstNode();
            LLNode<T> next = getFirstNode().getNext();
            if((n == 1) || (n == 2)) {
                for (int i = 0; i <= n; i++) {
                    this.swap(first, next);
                    next = next.getNext();
                }
            }
            else {
                for (int i = 0; i <= n + 2; i++) {
                    this.swap(first, next);
                    next = next.getNext();
                }
            }
        }
        return this;
    }

    public LinkedList<T> moveFirstToLast() {
        /* If linked list is empty, or it contains
            only one node, then nothing needs to be
            done, */
        if((getFirstNode() != null) && (getFirstNode().getNext() != null)) {
            //Initialize first and last pointers
            LLNode <T> first = getFirstNode();
            LLNode <T> last = getFirstNode();
            /*After this loop last contains address
              of last node in Linked List */
            while (last.getNext() != null) {
                last = last.getNext();
            }
            /* Change the head pointer to point
               to second node now */
            setFirstNode(first.getNext());
            /* Set the next of first as null */
            first.setNext(null);
            /* Set the next of last as first */
            last.setNext(first);
        }
        return this;
    }

    public LinkedList<T> moveLastToFirst() {
        if((getFirstNode() != null) && (getFirstNode().getNext() != null)) {
            /* Initialize second last and last pointers */
            LLNode<T> secLast = null;
            LLNode<T> last = getFirstNode();
            /* After this loop secLast contains address of
           second last  node and last contains address of
           last node in Linked List */
            while (last.getNext() != null) {
                secLast = last;
                last = last.getNext();
            }
            /* Set the next of second last as null */
            secLast.setNext(null);

            /* Set the next of last as head */
            last.setNext(getFirstNode());

            /* Change head to point to last node. */
            setFirstNode(last);
        }
        return this;
    }

    public LinkedList<T> reverseList() {
        //Initialize there pointers for previous, current and next node
        LLNode<T> prev = null;
        LLNode<T> current = getFirstNode();
        LLNode<T> next = null;
        //Iterate through the linked list
        while(current != null) {
            //Store the next node in next
            next = current.getNext();
            //Update the next pointer of curr to the prev
            current.setNext(prev);
            //Update prev as curr and curr as next
            prev = current;
            current = next;
        }
        //Update the first node to prev
        setFirstNode(prev);
        return this;
    }

    public LinkedList<T> reverseFirstK(int k) {
        // traverse the linked list until break
        // point not meet
        LLNode<T> temp = getFirstNode();
        int count = 1;
        while (count < k)
        {
            temp = temp.getNext();
            count++;
        }
        // backup the joint point
        LLNode<T> joint_point = temp.getNext();
        // break the list
        temp.setNext(null);
        // reverse the list till break point
        LLNode<T> prev = null;
        LLNode<T> current = getFirstNode();
        LLNode<T> next;
        while (current != null)
        {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        // join both parts of the linked list
        // traverse the list until null is not
        // found
        setFirstNode(prev);
        current = getFirstNode();
        while (current.getNext() != null)
            current = current.getNext();

        // joint both part of the list
        current.setNext(joint_point);
        return this;
    }
    public static ArrayList<Card> cardArray() {
        String[] nameArray = {"King Louis VI", "Marie Antoinette", "Regent", "Duke", "Baron", "Count", "Countess", "Lord", "Lady", "Cardinal", "Archbishop", "Nun", "Bishop", "Priest", "Priest", "Heretic", "Governor", "Mayor", "Councilman", "Judge", "Judge", "Tax Collector", "Sheriff", "Sheriff", "Palace Guard", "Palace Guard", "Palace Guard", "Palace Guard", "Palace Guard", "General", "Colonel", "Captain", "Lieutenant", "Lieutenant", "Tragic Figure", "Heroic Figure", "Student", "Student", "Student", "Student"};
        String[] groupArray = {"Royal", "Royal", "Royal", "Royal", "Royal", "Royal", "Royal", "Royal", "Royal", "Church", "Church", "Church", "Church", "Church", "Church", "Church", "Civic", "Civic", "Civic", "Civic", "Civic", "Civic", "Civic", "Civic", "Military", "Military", "Military", "Military", "Military", "Military", "Military", "Military", "Military", "Military", "Commoner", "Commoner", "Commoner", "Commoner", "Commoner", "Commoner"};
        String[] pointsArray = {"5", "5", "4", "3", "3", "2", "2", "2", "2", "5", "4", "3", "2", "1", "1", "*", "4", "3", "3", "2", "2", "*", "1", "1", "*", "*", "*", "*", "*", "4", "3", "2", "1", "1", "*", "-3", "-1", "-1", "-1", "-1"};
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            cards.add(new Card(nameArray[i], groupArray[i], pointsArray[i]));
        }
        return cards;
    }
    public static LinkedHashSet<Integer> set() {
        Random randNum = new Random();
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < 40) {
            set.add(randNum.nextInt(40));
        }
        return (LinkedHashSet<Integer>) set;
    }

    public LinkedList<T> cardsLinkedList() {
        for(int i = 0; i < 20; i++) {
            int num = Integer.parseInt(LinkedList.set().toArray()[i].toString());
            this.addToFront((T) LinkedList.cardArray().get(num));
        }
        return this;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addToFront(5);
        list.addToFront(4);
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        list.moveBack(5);
        System.out.println(list);
    }
}

