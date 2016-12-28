package interviewbit.math;

import java.util.*;

/**
 * Created by shahbaaz on 12/27/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/prime-sum/
     * @param a
     * @return
     */
    public ArrayList<Integer> primesum(int a) {

        boolean[] sieve = new boolean[a+1];
        Arrays.fill(sieve, true);

        for(Integer i = 2; i*i <= a; i++) {
            if (sieve[i]) {
                for (Integer j = i * 2; j <= a; j += i) {
                    sieve[j] = false;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<Integer>();

        for(Integer i = 2; i < a-1; i++) {
            if(sieve[i] && sieve[a-i]) {
                arr.add(i);
                arr.add(a-i);
                return arr;
            }
        }

        return null;
    }

    /**
     * https://www.interviewbit.com/problems/excel-column-number/
     * @param a
     * @return
     */
    public int titleToNumber(String a) {
        char[] s = a.toCharArray();

        int column = 0;
        for(int i = 0; i < s.length; i++) {
            column *= 26;
            column += (s[i] - 'A' + 1);
        }

        return column;
    }

    /**
     * https://www.interviewbit.com/problems/palindrome-integer/
     * @param a
     * @return
     */
    public boolean isPalindrome(int a) {
        int b = a, c = 0;
        while (b > 0) {
            c *= 10;
            c += b%10;
            b /= 10;
        }
        return (a == c);
    }

    /**
     * https://www.interviewbit.com/problems/greatest-common-divisor/
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        if (b > a) return gcd(b, a);
        if (b == 0) return a;
        if (a % b == 0) return b;
        return gcd(b, a%b);
    }

    /**
     * https://www.interviewbit.com/problems/rearrange-array/
     * @param a
     */
    public void arrange(ArrayList<Integer> a) {
        for(int i = 0; i < a.size(); i++) {
            if (a.get(i) <= 0) continue;
            int j = i, b = a.get(i);
            do {
                int k = Math.abs(a.get(j));
                a.set(j, -a.get(k));
                j = k;
            } while (a.get(j) != i);
            a.set(j, - b);
        }

        for(int i = 0; i < a.size(); i++) {
            if(a.get(i) < 0) a.set(i, -a.get(i));
        }
    }

    /**
     * https://www.interviewbit.com/problems/grid-unique-paths/
     * @param a
     * @param b
     * @return
     */
    public int uniquePaths(int a, int b) {
        int sum = 1;
        for(int i = a; i < a+b-1; i++) {
            sum *= i;
            sum /= (i-a+1);
        }
        return sum;
    }

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(4);
        a.add(0);
        a.add(2);
        a.add(1);
        a.add(3);

        new Solution().arrange(a);

        System.out.println(a);
    }
}
