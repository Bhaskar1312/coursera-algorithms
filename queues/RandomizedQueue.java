/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i  < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last  = n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == q.length) resize(2*q.length);
        q[last++] = item;
        if (last == q.length) last = 0;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int index = StdRandom.uniform(first, n + first); // first <= < size + first
        index %= q.length;
        Item swap = q[index];
        q[index] = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) first = 0;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return swap;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int index = StdRandom.uniform(first, n + first); // first <= < first + size
        index %= q.length;
        return q[index];
    }

    // return an independent iterator over items in "random" order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = 0;
            public boolean hasNext() {
                return i < n;
            }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = q[(i + first) % q.length];
                i++;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 10;
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; i < n; i++) {
            rq.enqueue(String.valueOf(i));
        }
        StdOut.println(rq.sample());
        while (!rq.isEmpty()) {
            StdOut.print(rq.dequeue()+" ");
        }
    }

}
