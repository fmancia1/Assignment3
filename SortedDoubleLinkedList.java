/**
* This is the SortedDoubleLinkedList<T> class
*
*
* @author Fatima Mancia
*
*/
import java.util.Comparator;
import java.lang.UnsupportedOperationException;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    Comparator<T> c;

    public SortedDoubleLinkedList(java.util.Comparator<T> comparator) {
        super();
        c = comparator;
    }
  /**
	 * add adds an element to the list
	 * @param data saves the content of each element of the list
	 * @return this
	 */    
    public SortedDoubleLinkedList<T> add(T data) {
        Node newNode = new Node(data);
        Node current = head.next;
        boolean added = false;
        while (current != tail) {
            if (c.compare(data, current.data) < 0) {
                newNode.prev = current.prev;
                newNode.prev.next = newNode;
                newNode.next = current;
                current.prev = newNode;
                added = true;
                break;
            }
            current = current.next;
        }
        if (!added) {
           newNode.prev = tail.prev;
           newNode.next = tail;
           newNode.prev.next = newNode;
           tail.prev = newNode;
        }
        return this;
    }
  /**
	 * addToEnd adds an element to the end of the list
	 * @param data saves the content of each element of the list
	 * 
	 */
    public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("addToEnd is a invalid operation");
    }
  /**
	 * addToFront adds an element to the end of the list
	 * @param data saves the content of each element of the list
	 * @return this
	 */
    public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("addToEnd is a invalid operation");
    }

    public ListIterator<T> iterator() {
        return super.iterator();
    }

    public SortedDoubleLinkedList<T> remove(T data, java.util.Comparator<T> comparator) {
       return (SortedDoubleLinkedList) super.remove(data, comparator);
    }
}