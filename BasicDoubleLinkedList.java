/**
* This is the BasicDoubleLinkedList<T> class
*
*
* @author Fatima Mancia
*
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.NoSuchElementException; 
import java.lang.UnsupportedOperationException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
  
  protected Node head;
  protected Node tail;
  protected int size;
  
  protected class Node {
    T data;
    Node prev;
    Node next;
    
    public Node(T data) {
      this.data= data;
      next = null;
      prev = null;
    }
  }

  public BasicDoubleLinkedList() {
    // head and tail do not contain any data
    head = new Node(null);
    tail = new Node(null);
    // At the beginning, they point each other
    head.next = tail;
    tail.prev = head;
    // The list starts empty
    size = 0;
  }
/**
	 * getSize number of elements on the List
	 * @return the number of elements in the Stack
	 */
  public int getSize() {
    return size;
  }
  /**
	 * addToEnd adds an element to the end of the list
	 * @param data saves the content of each element of the list
	 * @return this
	 */
  public BasicDoubleLinkedList<T> addToEnd(T data) {
    Node newNode = new Node(data);
    if (tail.prev == null) {
      // The list is empty
      tail.prev = newNode;
      head.next = newNode;
      newNode.prev = head;
      newNode.next = tail;
    }
    else {
      newNode.prev = tail.prev;
      newNode.next = tail;
      tail.prev.next = newNode;
      tail.prev = newNode;
    }
    size++;
    return this;
  }
  /**
	 * addToFront adds an element to the front of the list
	 * @param data saves the content of each element of the list
	 * @return this
	 */
  public BasicDoubleLinkedList<T> addToFront(T data) {
    Node newNode = new Node(data);
    if (head.next == null) {
      // The list is empty
      head.next = newNode;
      tail.prev = newNode;
      newNode.next = head;
      newNode.prev = tail;
    }
    else {
      newNode.next = head.next;
      newNode.prev = tail;
      head.next.prev = newNode;
      head.next = newNode;
    }
    size++;
    return this;
  }
  /**
	 * getFirst gets the first element of the list
	 *
	 * @return null if head = tail. If not return first element
	 */
  public T getFirst() {
    if (head.next == tail) {
      return null;
    }
    else {
      return head.next.data;
    }
  }
  /**
	 * getLast gets the last element of the list
	 *
	 * @return null if head = tail. If not return last element
	 */
  public T getLast() {
    if (tail.prev == head) {
      return null;
    }
    else {
      return tail.prev.data;
    }
  }
  /**
	 * remove removes elements
	 * @param targetData the element to get
	 * @return this
	 */
  public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
    Node current = head.next;
    boolean removed = false;
    while (current != tail) {
      if (comparator.compare(current.data, targetData) == 0) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        removed = true;
        break;
      }
      current = current.next;
    }
    return this;
  }
  /**
	 * retrieveFirstElement get the first element
	 * 
	 * @return null or the first data element
	 */
  public T retrieveFirstElement() {
    if (head.next == tail) {
      return null;
    }
    else {
      Node first = head.next;
      head.next = first.next;
      head.next.prev = head;
      size--;
      return first.data;
    }
  }
  /**
	 * retrieveLastElement get the first element
	 * 
	 * @return null or the last data element
	 */
  public T retrieveLastElement() {
    if (tail.prev == head) {
      return null;
    }
    else {
      Node last = tail.prev;
      tail.prev = last.prev;
      tail.prev.next = tail;
      size--;
      return last.data;
    }
  }
  /**
	  * Fills the list with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * 
	  * @return the array
	  */
  public ArrayList<T> toArrayList() {
    ArrayList<T> arr = new ArrayList<T>();
    Node current = head.next;
    while (current != tail) {
      arr.add(current.data);
      current = current.next;
    }
    return arr;
  }

  public ListIterator<T> iterator() throws java.lang.UnsupportedOperationException, java.util.NoSuchElementException {
    ListIterator<T> it = null;
    it  = new BasicDoubleLinkedListIterator();
    return it;
  }

  protected class BasicDoubleLinkedListIterator implements ListIterator<T> {
    
    Node current;
    
    public BasicDoubleLinkedListIterator() {
      current = head;
    }
   /**
	 * hasNext evaluates if the stack has other element.
   * 
	 * @return true if it is empty. If not return false.
	 *
	 */
    public boolean hasNext()  {
      return  (current.next != tail) && (current.next != null);
    }
   /**
	 * next evaluates if the stack has other element.
   * 
	 * @return data.
	 *
	 */
    public T next() /* throws UnsupportedOperationException */ {
      if (!hasNext()) {
        throw new NoSuchElementException("Operation not supported");
      }
      current = current.next;
      T data = current.data;
      return data;
    }
   /**
	 * hasPrevious evaluates if the list has a previous element.
   * 
	 * @return true if it is empty. If not return false.
	 *
	 */
    public boolean hasPrevious() {
        return (current.prev != head) && (current.prev != null);
    }
   /**
	 * previous evaluates if the list has a previous element.
   * 
	 * @return data.
	 *
	 */
    public T previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException("Operation not supported");
      }
      T data = current.data;
      current = current.prev;
      return data;
    }
   /**
	 * remove removes elements
	 * @param data
	 * 
	 */
    public void add(T data) {
      throw new UnsupportedOperationException("Operation not supported");
    }
   /**
	 * remove removes elements
	 * @param data
	 * 
	 */
    public void set(T data) {
      throw new UnsupportedOperationException("Operation not supported");
    }
   /**
	 * remove removes elements
	 * @param data
	 * 
	 */
    public void remove() {
      throw new UnsupportedOperationException("Operation not supported");
      
    }
   /**
	 * remove removes elements
	 * @param data
	 * 
	 */
    public int previousIndex() {
      throw new UnsupportedOperationException("Operation not supported");
    }
   /**
	 * remove removes elements
	 * @param data
	 * 
	 */    
    public int nextIndex() {
      throw new UnsupportedOperationException("Operation not supported");
    }
  }
}