import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;
    
    public IUDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public void addToFront(T element) {
         Node<T> newNode = new Node<T>(element);

        if (size > 0) { // or head != null, or !isEmpty()
            head.setPrev(newNode);
        } else {
            tail = newNode;
        }

        newNode.setNext(head);
        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToRear'");
    }

    @Override
    public void add(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void addAfter(T element, T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T retVal = tail.getElement();

        if ( size > 1) {
            tail.getPrev().setNext(null);
        } else {
            head = null;
        }

        tail = tail.getPrev();
        size--;
        modCount++;
        return retVal;
    }

    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }
    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    @Override
    public int indexOf(T element) {
        int retIndex = -1;

        int i = 0;
        Node<T> currNode = head;

        while (currNode != null && retIndex < 0) {
            if (currNode.getElement().equals(element)) {
                retIndex = i;
            } else {
                currNode = currNode.getNext();
                i++;
            }

        }

        return retIndex;
    }
    @Override
    public T first() {
        return head.getElement();
    }
    @Override
    public T last() {
        return tail.getElement();
    }

    @Override
    public boolean contains(T target) {
        return indexOf(target) > -1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
}


