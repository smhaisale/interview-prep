package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shahbaaz on 1/1/17.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/max-product-subarray/
     * @param a
     * @return
     */
    public int maxProduct(final List<Integer> a) {
        ArrayList<Integer> largest = new ArrayList<Integer>(), smallest = new ArrayList<Integer>();

        largest.add(a.get(0));
        smallest.add(a.get(0));

        for(int i = 1; i < a.size(); i++) {
            int n = a.get(i);
            if (n < 0) {
                largest.add(Math.max(n * smallest.get(i-1), n));
                smallest.add(Math.min(n * largest.get(i-1), n));
            } else {
                largest.add(Math.max(n, n * largest.get(i-1)));
                smallest.add(Math.min(n, n * smallest.get(i-1)));
            }
        }

        int max = largest.get(0);
        for(int i = 0; i < largest.size(); i++) max = Math.max(max, largest.get(i));

        return max;
    }

    /**
     * https://www.interviewbit.com/problems/ways-to-decode/
     * @param a
     * @return
     */
    public int numDecodings(String a) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (a.charAt(0) != '0') result.add(1);
        else return 0;

        if (a.charAt(0) == '1') {
            result.add(2);
        } else if (a.charAt(0) == '2' && a.charAt(1) <= '6') {
            result.add(2);
        } else {
            result.add(1);
        }

        for(int i = 2; i < a.length(); i++) {
            if (a.charAt(i-1) == '1') {
                result.add(result.get(i-1) + result.get(i-2));
            } else if (a.charAt(i-1) == '2' && a.charAt(i) <= '6') {
                result.add(result.get(i-1) + result.get(i-2));
            } else if (a.charAt(i-1) == '0') {
                result.add(0);
            } else {
                result.add(result.get(i-1));
            }
        }

        return result.get(a.length() - 1);
    }
}
