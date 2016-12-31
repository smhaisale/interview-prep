package interviewbit.bitmanipulation;

import java.util.List;

/**
 * Created by shahbaaz on 12/29/16.
 */
public class Solution {

    /**
     * https://www.interviewbit.com/problems/number-of-1-bits/
     * @param a
     * @return
     */
    public int numSetBits(long a) {
        int count = 0;
        while (a > 0) {
            if (a%2 == 1) count++;
            a >>= 1;
        }
        return count;
    }

    /**
     * https://www.interviewbit.com/problems/single-number/
     * @param a
     * @return
     */
    public int singleNumber(final List<Integer> a) {
        int element = 0;
        for(int i = 0; i < a.size(); i++)
            element = a.get(i) ^ element;

        return element;
    }

}
