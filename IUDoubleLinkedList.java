import java.util.ConcurrentModificationException;
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
        // recommend writing normally first, then once ListIter is completely correct
        // comment out old method and try with ListIters
        // no point difference using ListIters or normal method writing

        if (isEmpty()) {
            addToFront(element);
        } else {
            Node<T> newNode = new Node<T> (element);
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
        modCount++;
    }

    @Override
    public void add(T element) {
        addToRear(element);
    }

    @Override
    public void addAfter(T element, T target) {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<T> currNode = head;

        while (currNode.getNext() != null && currNode.getElement() != target) { // should end at currNode = target
            currNode = currNode.getNext();
        }

        Node<T> newNode = new Node<T>(element);

        newNode.setNext(currNode.getNext()); // set nN next

        if(newNode.getNext() != null) { // checks to see if its the last element in the list
            newNode.getNext().setPrev(newNode); // set next nodes prev to the new node
        } else { // if it is the last element, its the new tail
            tail = newNode;
        }
        newNode.setPrev(currNode);  // set the nN prev to currNode
        currNode.setNext(newNode);  // finally set the currNode next to newNode.
        
        size++;
        modCount++;
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T removeFirst() {
        ListIterator<T> itr = listIterator();
        
        T retVal = itr.next();
        itr.remove();

        return retVal;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T retVal = tail.getElement();

        if (size > 1) {
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

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<T> currNode;

        if (index < size / 2) {
            currNode = head;

            for (int i = 0; i < index; i++) {
                currNode = currNode.getNext();
            }
        } else {
            currNode = tail;

            for (int i = size - 1; i > index; i--) {
                currNode = currNode.getPrev();
            }
        }

        return currNode.getElement();
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
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DLLIterator(startingIndex);
    }

    private class DLLIterator implements ListIterator<T> {
        // dont keep a prevNode reference
        private Node<T> nextNode;
        private int nextIndex;
        private int iterModCount;
        private Node<T> lastRetNode;

        // private boolean canRmvNext; // don't do this because he said its the most
        // complicated

        public DLLIterator() {
            // uses the other constructor to reduce code-duplication, not too important
            // for this contstructor, but future ones
            this(0);
        }

        // Initializes BEFORE the specified index, so next() would return El at
        // startIndex
        public DLLIterator(int startIndex) {

            // at size is fine, would just put the iterator after last element so next()
            // would return null, hasNext() == false
            if (startIndex < 0 || startIndex > size) {
                throw new IndexOutOfBoundsException("Invalid start index");
            }

            nextNode = head;
            // want it to = startIndex to not add 1n to the for loop
            nextIndex = startIndex;
            iterModCount = modCount;

            // Advance to the starting index
            for (int i = 0; i < startIndex; i++) { // only efficient in 1st half of list
                nextNode = nextNode.getNext();
            }
            lastRetNode = null;

        }

        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T retVal = nextNode.getElement();

            lastRetNode = nextNode; // sets it to the node with retVal before moving next Node

            nextNode = nextNode.getNext();
            nextIndex++;

            return retVal;
        }

        @Override
        public boolean hasPrevious() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != head; // nextIndex > 0 works, but hard to keep track.
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (nextNode != null) {
                // this wouldn't work on tail since there is no nextNode without checking
                nextNode = nextNode.getPrev();
            } else {
                nextNode = tail;
            }

            lastRetNode = nextNode; // sets it to the node
            nextIndex--;
            return nextNode.getElement();
        }

        @Override
        public int nextIndex() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex - 1;
        }

        @Override
        public void set(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

        @Override
        public void add(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }

        @Override
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (lastRetNode == null) {
                throw new IllegalStateException();
            }

            if (lastRetNode != head) {
                lastRetNode.getPrev().setNext(lastRetNode.getNext());
            } else {
                head = lastRetNode.getNext();
            }
            if (lastRetNode != tail) {
                lastRetNode.getNext().setPrev(lastRetNode.getPrev());
            } else {
                tail = lastRetNode.getPrev();
            }

            if (lastRetNode != nextNode) { // last move was next()
                nextIndex--; // fewer elements to the left that were there
            } else { // last move was previous()
                nextNode = lastRetNode.getNext(); // nextNode can't point at a removed node
            }

            lastRetNode = null;

            size--;
            
            modCount++;
            iterModCount++;
        }

    }
}
