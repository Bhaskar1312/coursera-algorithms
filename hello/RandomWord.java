/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String word, ans = null;
        int iteration = 1;
        while (!StdIn.isEmpty()) {
            word = StdIn.readString();
            if (iteration == 1) {
                ans = word;
            }
            if (StdRandom.bernoulli(1.0/iteration)) {
                ans = word;
            }
            iteration++;
        }
        StdOut.println(ans);

    }
}
