/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        String name1, name2;
        try {
            name1 = args[0];
            name2 = args[1];
            System.out.printf("Hello %s and %s\n", name1, name2);
            System.out.printf("Goodbye %s and %s\n", name2, name1);
        }catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
