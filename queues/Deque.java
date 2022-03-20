/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (last == null) {
            last = first;
        } else {
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        // first.prev = null;
        n--;
        if (first == null) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        // last.next = null;
        n--;
        if (last == null) first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;
        // private Node current = last;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            // current = current.prev;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // int n = 5;
        // Deque<String> dq = new Deque<>();
        // for (int i = 0; i < 5; i++) {
        //     dq.addFirst(String.valueOf(i));
        //     dq.addLast(String.valueOf(i));
        // }
        // while (!dq.isEmpty()) {
        //     StdOut.print(dq.removeFirst()+ " "); // any one else use dq size >= 2
        //     StdOut.print(dq.removeLast()+" ");
        // }
        // Iterator<String> iterator = dq.iterator();
        // while (iterator.hasNext()) {
        //     StdOut.print(iterator.next() + "-");
        // }
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        System.out.println(deque.isEmpty());
        System.out.println(deque.isEmpty());
        deque.removeFirst();
    }

}